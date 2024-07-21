package com.dev.esthomy.service;

import com.dev.esthomy.dto.ClientDto;
import com.dev.esthomy.dto.FindPartnerRequestDto;
import com.dev.esthomy.dto.ProposalDto;
import com.dev.esthomy.dto.request.CreateFindPartnerRequest;
import com.dev.esthomy.dto.response.CreateFindPartnerRequestResponse;
import com.dev.esthomy.dto.response.GetFindPartnerRequestsPageable;
import com.dev.esthomy.dto.response.GetFindPartnerRequestsPageableAdapterResponse;
import com.dev.esthomy.exception.BusinessException;
import com.dev.esthomy.exception.error.BusinessError;
import com.dev.esthomy.models.Client;
import com.dev.esthomy.models.FindPartnerRequest;
import com.dev.esthomy.models.enums.MemberRole;
import com.dev.esthomy.repository.FindPartnerRequestDataAdapter;
import com.dev.esthomy.repository.FindPartnerRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindPartnerRequestService {
    private final ClientService clientService;
    private final FindPartnerRequestRepository findPartnerRequestRepository;
    private final StorageService storageService;
    private final FindPartnerRequestDataAdapter findPartnerRequestDataAdapter;

    public CreateFindPartnerRequestResponse create(final String id,
                                                   final MemberRole role,
                                                   final CreateFindPartnerRequest request, List<MultipartFile> files) {
        if (role.equals(MemberRole.BROKER)) throw new BusinessException(BusinessError.INVALID_ROLE);

        final Client client = clientService.findById(id);

        final FindPartnerRequest findPartnerRequest = findPartnerRequestRepository.save(FindPartnerRequest.builder()
                .aestheticType(request.getAestheticType())
                .client(client)
                .description(request.getDescription())
                .preferredCity(request.getPreferredCity())
                .preferedDate(request.getPreferedDate())
                .isNeededAccommodation(request.isNeededAccommodation())
                .isNeededTransportation(request.isNeededTransportation())
                .build());

        storageService.uploadFiles(role, files, findPartnerRequest.getId());

        return CreateFindPartnerRequestResponse.builder()
                .id(findPartnerRequest.getId())
                .build();
    }

    public GetFindPartnerRequestsPageable get(final String id,
                                              final int pageSize,
                                              final int pageNumber) {
        final GetFindPartnerRequestsPageableAdapterResponse getFindPartnerRequestsPageableAdapterResponse = findPartnerRequestDataAdapter.getClientFindPartnerRequestPageable(id, pageSize, pageNumber);

        return GetFindPartnerRequestsPageable.builder()
                .list(findPartnerRequestList(getFindPartnerRequestsPageableAdapterResponse.getList()))
                .totalPages(getFindPartnerRequestsPageableAdapterResponse.getTotalPages())
                .totalElements(getFindPartnerRequestsPageableAdapterResponse.getTotalElements())
                .build();
    }


    public GetFindPartnerRequestsPageable getAll(final MemberRole role,
                                                 final int pageSize,
                                                 final int pageNumber) {
        if (role.equals(MemberRole.BROKER))
            throw new BusinessException(BusinessError.INVALID_ROLE);

        final GetFindPartnerRequestsPageableAdapterResponse getFindPartnerRequestsPageable = findPartnerRequestDataAdapter.getFindPartnerRequestPageable(pageSize, pageNumber);

        return GetFindPartnerRequestsPageable.builder()
                .list(findPartnerRequestList(getFindPartnerRequestsPageable.getList()))
                .totalPages(getFindPartnerRequestsPageable.getTotalPages())
                .totalElements(getFindPartnerRequestsPageable.getTotalElements())
                .build();
    }

    private List<FindPartnerRequestDto> findPartnerRequestList(final List<FindPartnerRequest> partnerRequests) {

        return partnerRequests.stream().map(pr -> FindPartnerRequestDto.builder()
                .id(pr.getId())
                .aestheticType(pr.getAestheticType())
                .isNeededAccommodation(pr.isNeededAccommodation())
                .isNeededTransportation(pr.isNeededTransportation())
                .preferedDate(pr.getPreferedDate())
                .preferredCity(pr.getPreferredCity())
                .description(pr.getDescription())
                .imageUrls(storageService.getImagesUrls(pr.getId()))
                .proposalDtoList(pr.getProposals().stream()
                        .map(dto -> ProposalDto.builder().price(dto.getPrice()).build()).toList())
                .client(ClientDto.toDto(pr.getClient()))
                .build()).toList();

    }

    private ClientDto getClient(final String clientId) {
        return clientService.getById(clientId);
    }

    public FindPartnerRequest getById(final String findPartnerRequestId) {
        return findPartnerRequestRepository.getById(findPartnerRequestId);
    }

    public FindPartnerRequestDto getFindPartnerRequest(String id) {
        return findPartnerRequestRepository.findById(id)
                .map(FindPartnerRequestDto::toDto)
                .orElseThrow(() -> new BusinessException(BusinessError.INVALID_ROLE));
    }
}
