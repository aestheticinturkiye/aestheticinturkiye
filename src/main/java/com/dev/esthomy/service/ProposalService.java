package com.dev.esthomy.service;


import com.dev.esthomy.constants.EmailTemplates;
import com.dev.esthomy.dto.BrokerDto;
import com.dev.esthomy.dto.ClientDto;
import com.dev.esthomy.dto.ProposalDto;
import com.dev.esthomy.dto.request.CreateProposalRequest;
import com.dev.esthomy.dto.response.CreateProposalResponse;
import com.dev.esthomy.dto.response.GetAllProposalsResponse;
import com.dev.esthomy.dto.response.GetProposalResponse;
import com.dev.esthomy.exception.BusinessException;
import com.dev.esthomy.exception.error.BusinessError;
import com.dev.esthomy.models.Broker;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class ProposalService {
    private final BrokerService brokerService;
    private final ProposalRepository proposalRepository;
    private final ClientService clientService;
    private final FindPartnerRequestService findPartnerRequestService;
    private final MailService mailService;

    public CreateProposalResponse create(final CreateProposalRequest createProposalRequest,
                                         final String memberId,
                                         final MemberRole role) {
        if (MemberRole.CLIENT.equals(role)) throw new BusinessException(BusinessError.INVALID_ROLE);

        final Broker broker = brokerService.findById(memberId);
        final FindPartnerRequest findPartnerRequest = findPartnerRequestService.getById(createProposalRequest.getFindPartnerRequestId());
        final ClientDto clientDto = ClientDto.toDto(findPartnerRequest.getClient());
        final Proposal proposal = proposalRepository.save(
                Proposal.builder()
                        .findPartnerRequest(findPartnerRequest)
                        .broker(broker)
                        .description(createProposalRequest.getDescription())
                        .accommodation(createProposalRequest.getAccommodation())
                        .city(createProposalRequest.getCity())
                        .transportation(createProposalRequest.getCity())
                        .operationDate(createProposalRequest.getOperationDate())
                        .price(createProposalRequest.getPrice())
                        .clientId(clientDto.getId())
                        .build());

        try {
            mailService.sendEmail(clientDto.getEmail(), clientDto.getName(), null, EmailTemplates.RECEIPT_MESSAGE);
        } catch (MessagingException | UnsupportedEncodingException e) {
            log.error("Failed to send email. Error: " + e.getMessage());
        }

        return CreateProposalResponse.builder()
                .id(proposal.getId()).build();
    }

    public GetProposalResponse get(final String id) {
        final Proposal proposal = proposalRepository.findById(id).orElseThrow(() -> new BusinessException(BusinessError.PROPOSAL_NOT_FOUND));
        return GetProposalResponse.builder()
                .proposal(ProposalDto.toDto(proposal))
                .build();
    }

    public GetAllProposalsResponse getAll(final String memberId, final MemberRole role) {
        log.info("role: {}", role.getValue());

        switch (role) {
            case CLIENT:
                final ClientDto client = clientService.getById(memberId);
                final List<Proposal> proposals = proposalRepository.getByClientId(client.getId());
                return getProposalResponse(proposals);
            case BROKER:
                final BrokerDto broker = brokerService.getById(memberId);
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

    public Proposal findById(final String id) {
        return proposalRepository.findById(id).orElseThrow(() -> new BusinessException(BusinessError.PROPOSAL_NOT_FOUND));
    }
}

