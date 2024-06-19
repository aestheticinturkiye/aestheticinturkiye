package com.dev.esthomy.service;

import com.dev.esthomy.dto.OperationDto;
import com.dev.esthomy.dto.ProposalDto;
import com.dev.esthomy.dto.request.CreateOperationRequest;
import com.dev.esthomy.dto.response.CreateOperationResponse;
import com.dev.esthomy.exception.BusinessException;
import com.dev.esthomy.jwt.model.JwtClaims;
import com.dev.esthomy.models.Operation;
import com.dev.esthomy.models.Proposal;
import com.dev.esthomy.models.enums.MemberRole;
import com.dev.esthomy.repository.OperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OperationService {
    private final ProposalService proposalService;
    private final OperationRepository operationRepository;

    public CreateOperationResponse create(final JwtClaims principal, final CreateOperationRequest createOperationRequest) {
        if (!principal.getRole().equals(MemberRole.CLIENT)) throw new BusinessException("You can not create operation");

        final ProposalDto proposal = proposalService.findById(createOperationRequest.getProposalId());

        final Operation operation = operationRepository.save(Operation.builder()
                .proposal(Proposal.builder()
                        .id(proposal.getId()).build()).build());

        return CreateOperationResponse.builder()
                .id(operation.getId()).build();
    }

    public Optional<OperationDto> getByOperationId(final String operationId) {
        final Optional<Operation> operation = operationRepository.findById(operationId);

        if (operation.isPresent()) {
            return operation.map(p -> OperationDto.builder()
                    .proposalId(p.getProposal().getId()).status(p.getStatus()).build());
        } else {
            throw new BusinessException("Operation not found");
        }
    }

}
