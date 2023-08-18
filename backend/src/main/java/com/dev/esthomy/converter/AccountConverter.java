package com.dev.esthomy.converter;

import com.dev.esthomy.dto.AccountDto;
import com.dev.esthomy.dto.OperationRequestDto;
import com.dev.esthomy.dto.request.accountRequests.CreateAccountRequest;
import com.dev.esthomy.dto.response.AccountListResponse;
import com.dev.esthomy.dto.response.CreateAccountResponse;
import com.dev.esthomy.models.Account;
import com.dev.esthomy.models.OperationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AccountConverter {
    private final OperationConverter operationConverter;

    public AccountDto toDto(Account account) {
        List<OperationRequestDto> operationRequestDtos = new ArrayList<>();
        List<OperationRequest> operationRequests = account.getOperationRequests();

        if (operationRequests != null) {
            operationRequestDtos = operationRequests.stream()
                    .map(operationConverter::toDto)
                    .collect(Collectors.toList());
        }
        return AccountDto.builder()
                .name(account.getName())
                .email(account.getEmail())
                .surname(account.getSurname())
                .age(account.getAge())
                .country(account.getCountry())
                .phone(account.getPhone())
                .password(account.getPassword())
                .gender(account.getGender())
                .operationRequestDtos(operationRequestDtos)
                .build();
    }

    public Account toModel(CreateAccountRequest createAccountRequest) {
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

    public CreateAccountResponse toResponse(AccountDto accountDto) {
        return CreateAccountResponse.builder()
                .name(accountDto.getName())
                .email(accountDto.getEmail())
                .build();
    }

    public List<AccountDto> toList(List<Account> fromModel) {
        return fromModel.stream().map(this::toDto).collect(Collectors.toList());
    }

    public AccountListResponse toResponseList(List<AccountDto> accountDtos) {
        return AccountListResponse.builder()
                .accountDtos(accountDtos)
                .build();
    }
}
