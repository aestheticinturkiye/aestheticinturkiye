package com.dev.esthomy.service;

import com.dev.esthomy.dto.AccountDto;
import com.dev.esthomy.dto.authentication.request.LoginRequest;
import com.dev.esthomy.dto.authentication.response.LoginResponse;
import com.dev.esthomy.jwt.builder.JwtTokenBuilder;
import com.dev.esthomy.jwt.model.JwtClaims;
import com.dev.esthomy.jwt.model.JwtTokens;
import com.dev.esthomy.models.MemberRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final AccountService accountService;
    private final JwtTokenBuilder jwtTokenBuilder;

    public LoginResponse login(final LoginRequest request) {

        final AccountDto accountDto = accountService.getAccountByEmailAddress(request.getEmailAddress());
        final JwtTokens jwtTokens = jwtTokenBuilder.build(JwtClaims.builder()
                        .id(accountDto.getId())
                        .email(accountDto.getEmail())
                        .role(MemberRole.valueOf(request.getRole()))
                .build());

        return LoginResponse.builder()
                .token(JwtTokens.builder()
                        .accessToken(jwtTokens.getAccessToken())
                        .refreshToken(jwtTokens.getRefreshToken())
                        .build())
                .build();
    }
}
