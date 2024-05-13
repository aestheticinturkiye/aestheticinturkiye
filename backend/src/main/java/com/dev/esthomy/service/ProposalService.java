package com.dev.esthomy.service;


import com.dev.esthomy.dto.BrokerDto;
import com.dev.esthomy.dto.ClientDto;
import com.dev.esthomy.dto.ProposalDto;
import com.dev.esthomy.dto.request.CreateProposalRequest;
import com.dev.esthomy.dto.response.CreateProposalResponse;
import com.dev.esthomy.dto.response.GetProposalResponse;
import com.dev.esthomy.jwt.model.JwtClaims;
import com.dev.esthomy.models.FindPartnerRequest;
import com.dev.esthomy.models.Proposal;
import com.dev.esthomy.models.enums.MemberRole;
import com.dev.esthomy.repository.ProposalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProposalService {
    private final BrokerService brokerService;
    private final ProposalRepository proposalRepository;
    private final ClientService clientService;
    private final FindPartnerRequestService findPartnerRequestService;

    public CreateProposalResponse create(final JwtClaims principal, final CreateProposalRequest createProposal) {
        if (!principal.getRole().equals(MemberRole.BROKER)) throw new RuntimeException("You can not send proposal");

        final BrokerDto brokerDto = brokerService.getByEmail(principal.getEmail());
        final FindPartnerRequest findPartnerRequest = findPartnerRequestService.getById(createProposal.getFindPartnerRequestId());
        final Proposal proposal = proposalRepository.save(
                Proposal.builder()
                        .findPartnerRequest(findPartnerRequest)
                        .brokerId(brokerDto.getId())
                        .description(createProposal.getDescription())
                        .accommodation(createProposal.getAccommodation())
                        .city(createProposal.getCity())
                        .transportation(createProposal.getCity())
                        .operationDate(createProposal.getOperationDate())
                        .price(createProposal.getPrice())
                        .build());
        return CreateProposalResponse.builder()
                .id(proposal.getId()).build();
    }

    public GetProposalResponse get(JwtClaims principal) {
        if (!principal.getRole().equals(MemberRole.BROKER)) throw new RuntimeException("You can not send proposal");
        final BrokerDto brokerDto = brokerService.getByEmail(principal.getEmail());
        final List<Proposal> proposals = proposalRepository.getByBrokerId(brokerDto.getId());
        return getProposalResponse(proposals);

    }

    public GetProposalResponse getByClientId(JwtClaims principal) {
        if (!principal.getRole().equals(MemberRole.CLIENT)) throw new RuntimeException("You can not send proposal");
        final ClientDto clientDto = clientService.getByEmail(principal.getEmail());
        final List<Proposal> proposals = proposalRepository.getByClientId(clientDto.getId());
        return getProposalResponse(proposals);

    }

    public GetProposalResponse getProposalResponse(List<Proposal> proposals) {
        final List<ProposalDto> proposalDtos = proposals.stream().map(pr -> ProposalDto.builder()
                .price(pr.getPrice())
                .operationDate(pr.getOperationDate())
                .accommodation(pr.getAccommodation())
                .transportation(pr.getTransportation())
                .city(pr.getCity())
                .hospital(pr.getHospital())
                .description(pr.getDescription())
                .build()).toList();

        return GetProposalResponse.builder()
                .proposals(proposalDtos).build();
    }


    //ToDo : return type must be ResponseDto !
    public ProposalDto findById(final String id) {

        Optional<Proposal> proposal = proposalRepository.findById(id);

        if (proposal.isPresent()) {
            return proposal.map(p -> ProposalDto.builder().price(p.getPrice()).build()).orElse(null);
        }
        throw new RuntimeException("There is no record for this Proposal id");

    }
}

