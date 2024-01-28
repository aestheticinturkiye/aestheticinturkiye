package com.dev.esthomy.converter;

import com.dev.esthomy.dto.AccountDto;
import com.dev.esthomy.dto.request.accountRequests.CreateAccountRequest;
import com.dev.esthomy.dto.response.CreateAccountResponse;
import com.dev.esthomy.models.Account;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountConverter {
    public AccountDto toDto(final Optional<Account> account){
        return account.map(x -> AccountDto.builder()
                .id(x.getId())
                .name(x.getName())
                .email(x.getEmail())
                .surname(x.getSurname())
                .age(x.getAge())
                .country(x.getCountry())
                .phone(x.getPhone())
                .password(x.getPassword())
                .gender(x.getGender())
                .build()
               ).orElseThrow(()-> new RuntimeException("Account not found"));
    }

    public Account toModel(CreateAccountRequest createAccountRequest){
        return Account.builder()
                .age(createAccountRequest.getAge())
                .country(createAccountRequest.getCountry())
                .gender(createAccountRequest.getGender())
                .phone(createAccountRequest.getPhone())
                .password(createAccountRequest.getPassword())
                .email(createAccountRequest.getEmail())
                .surname(createAccountRequest.getSurname())
                .name(createAccountRequest.getName())
                .build();
    }

    public CreateAccountResponse toResponse(AccountDto accountDto){
        return CreateAccountResponse.builder()
                .name(accountDto.getName())
                .email(accountDto.getEmail())
                .build();
    }
}
