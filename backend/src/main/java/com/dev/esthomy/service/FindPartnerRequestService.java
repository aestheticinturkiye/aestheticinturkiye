package com.dev.esthomy.service;

import com.dev.esthomy.dto.ClientDto;
import com.dev.esthomy.dto.FindPartnerRequestDto;
import com.dev.esthomy.dto.ProposalDto;
import com.dev.esthomy.dto.request.CreateFindPartnerRequest;
import com.dev.esthomy.dto.response.CreateFindPartnerRequestResponse;
import com.dev.esthomy.dto.response.GetFindPartnerRequestsPageable;
import com.dev.esthomy.dto.response.GetFindPartnerRequestsPageableAdapterResponse;
import com.dev.esthomy.exception.BusinessException;
import com.dev.esthomy.jwt.model.JwtClaims;
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

    public CreateFindPartnerRequestResponse create(final JwtClaims principal, final CreateFindPartnerRequest request, List<MultipartFile> files) {
        if (!principal.getRole().equals(MemberRole.CLIENT)) throw new BusinessException("You can not find partner");

        final ClientDto clientDto = clientService.getByEmail(principal.getEmail());

        final FindPartnerRequest findPartnerRequest = findPartnerRequestRepository.save(FindPartnerRequest.builder()
                .aestheticType(request.getAestheticType())
                .clientId(clientDto.getId())
                .description(request.getDescription())
                .preferredCity(request.getPreferredCity())
                .preferedDate(request.getPreferedDate())
                .isNeededAccommodation(request.isNeededAccommodation())
                .isNeededTransportation(request.isNeededTransportation())
                .build());

        storageService.uploadFiles(principal, files, findPartnerRequest.getId());

        return CreateFindPartnerRequestResponse.builder()
                .id(findPartnerRequest.getId())
                .build();
    }

    public GetFindPartnerRequestsPageable get(final JwtClaims principal,
                                              final int pageSize,
                                              final int pageNumber) {
        if (!principal.getRole().equals(MemberRole.CLIENT))
            throw new BusinessException("You can not reach find partner requests");

        final ClientDto clientDto = clientService.getByEmail(principal.getEmail());

        final GetFindPartnerRequestsPageableAdapterResponse getFindPartnerRequestsPageableAdapterResponse = findPartnerRequestDataAdapter.getClientFindPartnerRequestPageable(clientDto.getId(), pageSize, pageNumber);

        return GetFindPartnerRequestsPageable.builder()
                .list(findPartnerRequestList(getFindPartnerRequestsPageableAdapterResponse.getList()))
                .totalPages(getFindPartnerRequestsPageableAdapterResponse.getTotalPages())
                .totalElements(getFindPartnerRequestsPageableAdapterResponse.getTotalElements())
                .build();
    }


    public GetFindPartnerRequestsPageable getAll(final JwtClaims principal,
                                                 final int pageSize,
                                                 final int pageNumber) {
        if (!principal.getRole().equals(MemberRole.BROKER))
            throw new BusinessException("You can not reach find partner requests");

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
                .client(getClient(pr.getClientId()))
                .build()).toList();

    }

    private ClientDto getClient(final String clientId) {
        return clientService.getById(clientId);
    }

    public FindPartnerRequest getById(final String findPartnerRequestId) {
        return findPartnerRequestRepository.getById(findPartnerRequestId);
    }

}
