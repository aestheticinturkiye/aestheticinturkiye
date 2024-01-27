package com.dev.esthomy.service;

import com.dev.esthomy.dto.AccountDto;
import com.dev.esthomy.dto.authentication.request.AuthenticateWithRefreshTokenResponse;
import com.dev.esthomy.dto.authentication.request.LoginRequest;
import com.dev.esthomy.dto.authentication.response.LoginResponse;
import com.dev.esthomy.jwt.builder.JwtTokenBuilder;
import com.dev.esthomy.jwt.model.JwtClaims;
import com.dev.esthomy.jwt.model.JwtTokens;
import com.dev.esthomy.jwt.model.Token;
import com.dev.esthomy.jwt.resolver.RefreshTokenResolver;
import com.dev.esthomy.models.MemberRole;
import com.dev.esthomy.util.CookieBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final AccountService accountService;
    private final JwtTokenBuilder jwtTokenBuilder;
    private final CookieBuilder cookieBuilder;
    private final RefreshTokenResolver refreshTokenResolver;

    public ResponseEntity<LoginResponse> login(final LoginRequest request) {

        final AccountDto accountDto = accountService.getAccountByEmailAddress(request.getEmailAddress());
        final JwtTokens jwtTokens = jwtTokenBuilder.build(JwtClaims.builder()
                .id(accountDto.getId())
                .email(accountDto.getEmail())
                .role(MemberRole.valueOf(request.getRole()))
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
