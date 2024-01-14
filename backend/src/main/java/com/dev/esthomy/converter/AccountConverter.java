package com.dev.esthomy.converter;

import com.dev.esthomy.dto.AccountDto;
import com.dev.esthomy.dto.request.accountRequests.CreateAccountRequest;
import com.dev.esthomy.dto.response.CreateAccountResponse;
import com.dev.esthomy.models.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter {
    public AccountDto toDto(Account account){
        return AccountDto.builder()
                .id(account.getId())
                .name(account.getName())
                .email(account.getEmail())
                .surname(account.getSurname())
                .age(account.getAge())
                .country(account.getCountry())
                .phone(account.getPhone())
                .password(account.getPassword())
                .gender(account.getGender())
                .build();
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
