package com.dev.esthomy.service;

import com.dev.esthomy.converter.AccountConverter;
import com.dev.esthomy.dto.AccountDto;
import com.dev.esthomy.dto.request.accountRequests.CreateAccountRequest;
import com.dev.esthomy.dto.response.CreateAccountResponse;
import com.dev.esthomy.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountConverter accountConverter;
    public CreateAccountResponse create(CreateAccountRequest request) {
        final AccountDto accountDto =  accountConverter.toDto(Optional.of(accountRepository.save(accountConverter.toModel(request))));
        return accountConverter.toResponse(accountDto);
    }

    public AccountDto getAccountByEmailAddress(final String emailAddress) {
        return accountConverter.toDto(accountRepository.findByEmail(emailAddress));
    }
}
