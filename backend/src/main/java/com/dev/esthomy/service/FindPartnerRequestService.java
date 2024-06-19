package com.dev.esthomy.service;

import com.dev.esthomy.dto.ClientDto;
import com.dev.esthomy.dto.FindPartnerRequestDto;
import com.dev.esthomy.dto.ProposalDto;
import com.dev.esthomy.dto.request.CreateFindPartnerRequest;
import com.dev.esthomy.dto.response.CreateFindPartnerRequestResponse;
import com.dev.esthomy.dto.response.GetFindPartnerRequestsResponse;
import com.dev.esthomy.jwt.model.JwtClaims;
import com.dev.esthomy.models.FindPartnerRequest;
import com.dev.esthomy.models.enums.MemberRole;
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

    public CreateFindPartnerRequestResponse create(final JwtClaims principal, final CreateFindPartnerRequest request, List<MultipartFile> files) {
        if (!principal.getRole().equals(MemberRole.CLIENT)) throw new RuntimeException("You can not find partner");

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

    public GetFindPartnerRequestsResponse get(final JwtClaims principal) {
        if (!principal.getRole().equals(MemberRole.CLIENT))
            throw new RuntimeException("You can not reach find partner requests");

        final ClientDto clientDto = clientService.getByEmail(principal.getEmail());

        final List<FindPartnerRequest> partnerRequests = findPartnerRequestRepository.getByClientId(clientDto.getId());

        return getGetFindPartnerRequestsResponse(partnerRequests);
    }


    public GetFindPartnerRequestsResponse getAll(final JwtClaims principal) {
        if (!principal.getRole().equals(MemberRole.BROKER))
            throw new RuntimeException("You can not reach find partner requests");


        final List<FindPartnerRequest> partnerRequests = findPartnerRequestRepository.findAll();

        return getGetFindPartnerRequestsResponse(partnerRequests);
    }

    private GetFindPartnerRequestsResponse getGetFindPartnerRequestsResponse(final List<FindPartnerRequest> partnerRequests) {

        List<FindPartnerRequestDto> partnerRequestDtos = partnerRequests.stream().map(pr -> FindPartnerRequestDto.builder()
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

        return GetFindPartnerRequestsResponse.builder()
                .findPartnerRequests(partnerRequestDtos)
                .build();
    }

    private ClientDto getClient(String clientId) {
        return clientService.getById(clientId);
    }

    public FindPartnerRequest getById(final String findPartnerRequestId) {
        return findPartnerRequestRepository.getById(findPartnerRequestId);
    }

}
