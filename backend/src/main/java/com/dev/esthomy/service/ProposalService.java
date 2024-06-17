package com.dev.esthomy.service;


import com.dev.esthomy.constants.EmailTemplates;
import com.dev.esthomy.dto.BrokerDto;
import com.dev.esthomy.dto.ClientDto;
import com.dev.esthomy.dto.ProposalDto;
import com.dev.esthomy.dto.request.CreateProposalRequest;
import com.dev.esthomy.dto.response.CreateProposalResponse;
import com.dev.esthomy.dto.response.GetAllProposalsResponse;
import com.dev.esthomy.dto.response.GetProposalResponse;
import com.dev.esthomy.jwt.model.JwtClaims;
import com.dev.esthomy.models.FindPartnerRequest;
import com.dev.esthomy.models.Proposal;
import com.dev.esthomy.models.enums.MemberRole;
import com.dev.esthomy.repository.ProposalRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProposalService {
    private final BrokerService brokerService;
    private final ProposalRepository proposalRepository;
    private final ClientService clientService;
    private final FindPartnerRequestService findPartnerRequestService;
    private final MailService mailService;
    public CreateProposalResponse create(final JwtClaims principal, final CreateProposalRequest createProposal) {
        if (MemberRole.CLIENT.equals(principal.getRole())) throw new RuntimeException("You can not send proposal");

        final BrokerDto brokerDto = brokerService.getByEmail(principal.getEmail());
        final FindPartnerRequest findPartnerRequest = findPartnerRequestService.getById(createProposal.getFindPartnerRequestId());
        final ClientDto clientDto = clientService.getById(findPartnerRequest.getClientId());
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

        try {
            mailService.sendEmail(clientDto.getEmail(),clientDto.getName(),null, EmailTemplates.RECEIPT_MESSAGE);
        } catch (MessagingException | UnsupportedEncodingException e) {
            log.error("Failed to send email. Error: " + e.getMessage());
        }

        return CreateProposalResponse.builder()
                .id(proposal.getId()).build();
    }

    public GetProposalResponse get(final JwtClaims principal, final String id) {
        final Proposal proposal = proposalRepository.findById(id).orElseThrow(() -> new RuntimeException("There is no record for this Proposal id"));
        final MemberRole role = principal.getRole();

        if (MemberRole.BROKER.equals(role)) {
            final BrokerDto broker = brokerService.getByEmail(principal.getEmail());
            if (broker.getId().equals(proposal.getBrokerId())) {
                return GetProposalResponse.builder()
                        .proposal(ProposalDto.toDto(proposal))
                        .build();
            }
            log.error("broker ids not matched. brokerId: {}, proposalBrokerId: {}", broker.getId(), proposal.getBrokerId());
        }

        if (MemberRole.CLIENT.equals(role)) {
            final ClientDto client = clientService.getByEmail(principal.getEmail());
            if (client.getId().equals(proposal.getClientId())) {
                return GetProposalResponse.builder()
                        .proposal(ProposalDto.toDto(proposal))
                        .build();
            }
            log.error("broker ids not matched. brokerId: {}, proposalClientId: {}", client.getId(), proposal.getBrokerId());
        }

        throw new RuntimeException("You can not see this proposal");
    }

    public GetAllProposalsResponse getAll(final JwtClaims principal) {
        final MemberRole role = principal.getRole();
        log.info("role: {}", role.getValue());

        switch (role) {
            case CLIENT:
                final ClientDto client = clientService.getByEmail(principal.getEmail());
                final List<Proposal> proposals = proposalRepository.getByClientId(client.getId());
                return getProposalResponse(proposals);
            case BROKER:
                final BrokerDto broker = brokerService.getByEmail(principal.getEmail());
                final List<Proposal> brokerProposals = proposalRepository.getByBrokerId(broker.getId());
                return getProposalResponse(brokerProposals);
            default:
                throw new RuntimeException("An unexpected error occurred.");
        }
    }

    public GetAllProposalsResponse getProposalResponse(List<Proposal> proposals) {
        final List<ProposalDto> proposalDtos = proposals.stream().map(pr -> ProposalDto.builder()
                .price(pr.getPrice())
                .operationDate(pr.getOperationDate())
                .accommodation(pr.getAccommodation())
                .transportation(pr.getTransportation())
                .city(pr.getCity())
                .hospital(pr.getHospital())
                .description(pr.getDescription())
                .build()).toList();

        return GetAllProposalsResponse.builder()
                .proposals(proposalDtos).build();
    }

    public ProposalDto findById(final String id) {

        Optional<Proposal> proposal = proposalRepository.findById(id);

        if (proposal.isPresent()) {
            return proposal.map(p -> ProposalDto.builder().price(p.getPrice()).build()).orElse(null);
        }
        throw new RuntimeException("There is no record for this Proposal id");

    }
}

