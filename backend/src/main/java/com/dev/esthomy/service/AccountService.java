package com.dev.esthomy.service;

import com.dev.esthomy.dto.AccountDto;
import com.dev.esthomy.dto.BrokerDto;
import com.dev.esthomy.dto.ClientDto;
import com.dev.esthomy.dto.request.GetAccountRequest;
import com.dev.esthomy.dto.response.GetAccountResponse;
import com.dev.esthomy.jwt.model.JwtClaims;
import com.dev.esthomy.models.enums.MemberRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final BrokerService brokerService;
    private final ClientService clientService;

    public GetAccountResponse getSignedAccount(final JwtClaims principal, final GetAccountRequest request) {
        final MemberRole role = principal.getRole();
        AccountDto accountDto = null;
        if (role.equals(MemberRole.BROKER)) {
            final BrokerDto brokerDto = brokerService.getById(request.getId());
            accountDto = AccountDto.builder()
                    .id(brokerDto.getId())
                    .email(brokerDto.getEmail())
                    .age(brokerDto.getAge())
                    .name(brokerDto.getName())
                    .surname(brokerDto.getSurname())
                    .country(brokerDto.getCountry())
                    .phone(brokerDto.getPhone())
                    .gender(brokerDto.getGender())
                    .role(MemberRole.BROKER)
                    .build();
        }

        if (role.equals(MemberRole.CLIENT)) {
            final ClientDto clientDto = clientService.getById(request.getId());
            accountDto = AccountDto.builder()
                    .id(clientDto.getId())
                    .email(clientDto.getEmail())
                    .age(clientDto.getAge())
                    .name(clientDto.getName())
                    .surname(clientDto.getSurname())
                    .country(clientDto.getCountry())
                    .phone(clientDto.getPhone())
                    .gender(clientDto.getGender())
                    .role(MemberRole.CLIENT)
                    .build();
        }

        return GetAccountResponse.builder()
                .accountInfo(accountDto)
                .build();
    }
}
