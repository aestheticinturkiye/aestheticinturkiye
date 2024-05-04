package com.dev.esthomy.service;

import com.dev.esthomy.dto.AccountDto;
import com.dev.esthomy.dto.BrokerDto;
import com.dev.esthomy.dto.ClientDto;
import com.dev.esthomy.dto.CredentialDto;
import com.dev.esthomy.dto.authentication.request.AuthenticateWithRefreshTokenResponse;
import com.dev.esthomy.dto.authentication.request.LoginRequest;
import com.dev.esthomy.dto.authentication.response.LoginResponse;
import com.dev.esthomy.jwt.builder.JwtTokenBuilder;
import com.dev.esthomy.jwt.model.JwtClaims;
import com.dev.esthomy.jwt.model.JwtTokens;
import com.dev.esthomy.jwt.model.Token;
import com.dev.esthomy.jwt.resolver.RefreshTokenResolver;
import com.dev.esthomy.models.enums.MemberRole;
import com.dev.esthomy.util.CookieBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.dev.esthomy.models.enums.MemberRole.BROKER;
import static com.dev.esthomy.models.enums.MemberRole.CLIENT;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final ClientService clientService;
    private final JwtTokenBuilder jwtTokenBuilder;
    private final CookieBuilder cookieBuilder;
    private final RefreshTokenResolver refreshTokenResolver;
    private final CredentialService credentialService;
    private final BrokerService brokerService;

    public ResponseEntity<LoginResponse> login(final LoginRequest request) {

        final CredentialDto credentialDto = credentialService.checkCredentialOptional(request);
        final MemberRole role = credentialDto.getRole();
        AccountDto accountDto = null;
        if (role.equals(BROKER)) {
            final BrokerDto brokerDto = brokerService.getByEmail(credentialDto.getEmail());
            accountDto = AccountDto.builder()
                    .email(brokerDto.getEmail())
                    .role(BROKER)
                    .id(brokerDto.getId())
                    .build();
        }

        if (role.equals(CLIENT)) {
            final ClientDto clientDto = clientService.getByEmail(credentialDto.getEmail());
            accountDto = AccountDto.builder()
                    .email(clientDto.getEmail())
                    .role(CLIENT)
                    .id(clientDto.getId())
                    .build();
        }

        final JwtTokens jwtTokens = jwtTokenBuilder.build(JwtClaims.builder()
                .email(accountDto.getEmail())
                .role(role)
                .id(accountDto.getId())
                .build());

        final HttpHeaders httpHeaders = new HttpHeaders();
        addRefreshTokenCookie(httpHeaders, jwtTokens.getRefreshToken());
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(LoginResponse.builder()
                        .accessToken(jwtTokens.getAccessToken().getValue())
                        .build());

    }

    public ResponseEntity<AuthenticateWithRefreshTokenResponse> authenticateWithRefreshToken(final String refreshToken) {
        final JwtClaims jwtClaims = refreshTokenResolver.resolve(refreshToken);
        final JwtTokens jwtTokens = jwtTokenBuilder.build(jwtClaims);
        final HttpHeaders httpHeaders = new HttpHeaders();
        addRefreshTokenCookie(httpHeaders, jwtTokens.getRefreshToken());
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(AuthenticateWithRefreshTokenResponse.builder()
                        .accessToken(jwtTokens.getAccessToken().getValue())
                        .build());
    }

    //use it when you want to add access token inside the cookie
    private void addAccessTokenCookie(final HttpHeaders httpHeaders, final Token token) {
        httpHeaders.add(HttpHeaders.SET_COOKIE, cookieBuilder.buildAccessTokenCookie(token).toString());
    }

    private void addRefreshTokenCookie(final HttpHeaders httpHeaders, final Token token) {
        httpHeaders.add(HttpHeaders.SET_COOKIE, cookieBuilder.buildRefreshTokenCookie(token).toString());
    }
}
