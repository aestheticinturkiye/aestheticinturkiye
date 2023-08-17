package com.dev.esthomy.converter;

import com.dev.esthomy.dto.AccountDto;
import com.dev.esthomy.dto.AccountOperationRequestDto;
import com.dev.esthomy.dto.response.AccountListResponse;
import com.dev.esthomy.models.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@RequiredArgsConstructor
public class AccountOperationRequestConverter {
    public AccountOperationRequestDto toDto(Account account, List<String> operationRequests){
        return AccountOperationRequestDto.builder()
                .name(account.getName())
                .phone(account.getPhone())
                .email(account.getEmail())
                .operationId(operationRequests)
                .age(account.getAge())
                .country(account.getCountry())
                .surname(account.getSurname())
                .password(account.getPassword())
                .gender(account.getGender())
                .build();
    }
    public AccountListResponse toResponseList(List<AccountDto> accountDtos)
    {
        return AccountListResponse.builder()
                .accountDtos(accountDtos)
                .build();
    }
}
