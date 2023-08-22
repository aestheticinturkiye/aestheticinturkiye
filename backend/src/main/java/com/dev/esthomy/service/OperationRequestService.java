package com.dev.esthomy.service;

import com.dev.esthomy.converter.OperationConverter;
import com.dev.esthomy.dto.OperationRequestDto;
import com.dev.esthomy.dto.request.operationRequest.CreateOperationRequest;
import com.dev.esthomy.dto.response.OperationRequestListResponse;
import com.dev.esthomy.models.Account;
import com.dev.esthomy.models.OperationRequest;
import com.dev.esthomy.repository.OperationRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationRequestService {
    private final OperationConverter operationConverter;
    private final AccountService accountService;
    private final OperationRequestRepository operationRequestRepository;

    public OperationRequestDto create(CreateOperationRequest request) {

        Account account = accountService.getUserByMail(request.getEmail());

        return operationConverter.toDto(
                operationRequestRepository.save(
                        operationConverter.toModel(request, account)));
    }

    public OperationRequestListResponse getAll() {

        return operationConverter.toList(operationRequestRepository.findAll());
    }

    public OperationRequest getById(String operationRequestId) {
        return operationRequestRepository.findById(operationRequestId).orElse(null);
    }

    public List<OperationRequest> getAllByAccountId(String accountId) {
        return operationRequestRepository.findAllByUserAccountId(accountId);
    }

}