package com.dev.esthomy.service;

import com.dev.esthomy.converter.OperationConverter;
import com.dev.esthomy.dto.OperationRequestDto;
import com.dev.esthomy.dto.request.operationRequest.CreateOperationRequest;
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
        List<Account>  accounts = accountService.getAllUsers();
        Account account = accounts.stream()
                .filter(account1 -> account1.getEmail().equals(request.getEmail()))
                .findAny()
                .orElse(null);

        return operationConverter.toDto(
                operationRequestRepository.save(
                        operationConverter.toModel(request,account)),account);
    }

    public List<OperationRequest> getAll(){
        return operationRequestRepository.findAll();
    }

}