package com.dev.esthomy.service;

import com.dev.esthomy.converter.AccountConverter;
import com.dev.esthomy.dto.AccountDto;
import com.dev.esthomy.dto.request.accountRequests.CreateAccountRequest;
import com.dev.esthomy.dto.response.CreateAccountResponse;
import com.dev.esthomy.models.Account;
import com.dev.esthomy.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountConverter accountConverter;

    public AccountDto create(CreateAccountRequest request) {
        return accountConverter.toDto(accountRepository.save(accountConverter.toModel(request)));
    }

    public CreateAccountResponse createCreateResponse(CreateAccountRequest request) {
        return accountConverter.toResponse(this.create(request));
    }

    public List<AccountDto> getAllUsers() {
        return accountConverter.toList(accountRepository.findAll());
    }

    public Account getUserByMail(String email) {
        return accountRepository.findAccountByEmail(email);
    }


}
