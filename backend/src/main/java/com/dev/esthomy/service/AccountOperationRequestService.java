package com.dev.esthomy.service;

import com.dev.esthomy.models.OperationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountOperationRequestService {

    private final OperationRequestService operationRequestService;
    private final AccountService accountService;


    public List<String> getAllByAccountId(String accountId) {
        List<OperationRequest> operationRequests = operationRequestService.getAllByAccountId(accountId);
        return (List<String>) operationRequests.stream().map(a -> (a.getAestheticType().toString() + a.getUserAccount().getName().toString()) );
    }

//    public AccountOperationRequestDto getById(String accountId)
//    {
//        Account account = accountService.getById(accountId);
//        List<String> operationRequests = this.getAllByAccountId(accountId);
//
//        return accountOperationRequestConverter.toDto(account,operationRequests);
//    }

}
