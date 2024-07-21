package com.dev.esthomy.service;

import com.dev.esthomy.dto.OperationDto;
import com.dev.esthomy.dto.request.CreateOperationRequest;
import com.dev.esthomy.dto.response.CreateOperationResponse;
import com.dev.esthomy.exception.BusinessException;
import com.dev.esthomy.exception.error.BusinessError;
import com.dev.esthomy.models.Operation;
import com.dev.esthomy.models.Proposal;
import com.dev.esthomy.models.enums.MemberRole;
import com.dev.esthomy.models.enums.Status;
import com.dev.esthomy.repository.OperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OperationService {
    private final ProposalService proposalService;
    private final OperationRepository operationRepository;

    public CreateOperationResponse create(final CreateOperationRequest createOperationRequest,
                                          final MemberRole role) {
        if (MemberRole.CLIENT.equals(role)) throw new BusinessException(BusinessError.INVALID_ROLE);

        final Proposal proposal = proposalService.findById(createOperationRequest.getProposalId());

        final Operation operation = operationRepository.save(Operation.builder()
                .proposal(proposal)
                .status(Status.IN_PROGRESS)
                .build());

        return CreateOperationResponse.builder()
                .id(operation.getId()).build();
    }

    public Optional<OperationDto> getByOperationId(final String operationId) {
        final Optional<Operation> operation = operationRepository.findById(operationId);

        if (operation.isPresent()) {
            return operation.map(p -> OperationDto.builder()
                    .proposalId(p.getProposal().getId()).status(p.getStatus()).build());
        } else {
            throw new BusinessException(BusinessError.OPERATION_NOT_FOUND);
        }
    }

}
