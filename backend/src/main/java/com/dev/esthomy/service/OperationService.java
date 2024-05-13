package com.dev.esthomy.service;

import com.dev.esthomy.dto.ProposalDto;
import com.dev.esthomy.dto.request.CreateOperationRequest;
import com.dev.esthomy.dto.response.CreateOperationResponse;
import com.dev.esthomy.jwt.model.JwtClaims;
import com.dev.esthomy.models.Operation;
import com.dev.esthomy.models.Proposal;
import com.dev.esthomy.models.enums.MemberRole;
import com.dev.esthomy.repository.OperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OperationService {
    private final ProposalService proposalService;
    private final OperationRepository operationRepository;

    public CreateOperationResponse create(final JwtClaims principal, final CreateOperationRequest createOperationRequest) {
        if (!principal.getRole().equals(MemberRole.CLIENT)) throw new RuntimeException("You can create operation");

        final ProposalDto proposal = proposalService.findById(createOperationRequest.getProposalId());

        final Operation operation = operationRepository.save(Operation.builder()
                .proposal(Proposal.builder()
                        .id(proposal.getId()).build()).build());

        return CreateOperationResponse.builder()
                .id(operation.getId()).build();
    }
}
