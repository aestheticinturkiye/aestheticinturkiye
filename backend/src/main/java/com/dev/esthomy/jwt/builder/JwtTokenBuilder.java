package com.dev.esthomy.jwt.builder;

import com.dev.esthomy.jwt.configuration.JwtProperties;
import com.dev.esthomy.jwt.model.JwtClaims;
import com.dev.esthomy.jwt.model.JwtTokens;
import com.dev.esthomy.jwt.model.Token;
import io.jsonwebtoken.JwtBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtTokenBuilder {

    private final JwtProperties jwtProperties;
    @Qualifier("accessTokenBuilder")
    private final JwtBuilder accessTokenBuilder ;
    @Qualifier("refreshTokenBuilder")
    private final JwtBuilder refreshTokenBuilder;

    public JwtTokens build(final JwtClaims claims){
        var issuedAtMillis = System.currentTimeMillis();
        return JwtTokens.builder()
                .accessToken(buildAccessToken(claims, issuedAtMillis))
                .refreshToken(buildRefreshToken(claims, issuedAtMillis))
                .build();
    }

    private Token buildAccessToken(final JwtClaims claims, final long issuedAtMillis) {
        return Token.builder()
                .value(accessTokenBuilder
                        .setIssuer(jwtProperties.getIssuer())
                        .setIssuedAt(new Date(issuedAtMillis))
                        .setExpiration(new Date(issuedAtMillis + jwtProperties.getAccess().getExpiration().toMillis()))
                        .addClaims(Map.ofEntries(
                                Map.entry("role", claims.getRole()),
                                Map.entry("email", claims.getEmail()),
                                Map.entry("id", claims.getId())
                        ))
                        .compact())
                .duration(jwtProperties.getAccess().getExpiration().toMillis())
                .build();
    }
    private Token buildRefreshToken(final JwtClaims claims, final long issuedAtMillis) {
        return Token.builder()
                .value(refreshTokenBuilder
                        .setIssuer(jwtProperties.getIssuer())
                        .setIssuedAt(new Date(issuedAtMillis))
                        .setExpiration(new Date(issuedAtMillis + jwtProperties.getRefresh().getExpiration().toMillis()))
                        .addClaims(Map.ofEntries(
                                Map.entry("role", claims.getRole()),
                                Map.entry("email", claims.getEmail()),
                                Map.entry("id", claims.getId())
                        ))
                        .compact())
                .duration(jwtProperties.getRefresh().getExpiration().toMillis())
                .build();
    }
}
