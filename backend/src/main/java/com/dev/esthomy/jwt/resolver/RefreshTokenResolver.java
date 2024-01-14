package com.dev.esthomy.jwt.resolver;

import com.dev.esthomy.jwt.model.JwtClaims;
import com.dev.esthomy.models.MemberRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenResolver {
    private final JwtParser jwtParser;

    public RefreshTokenResolver(final @Qualifier("refreshTokenParser") JwtParser jwtParser) {
        this.jwtParser = jwtParser;
    }

    private JwtClaims resolve(final String refreshToken) {
        final Claims claims = jwtParser.parseClaimsJws(refreshToken).getBody();
        return JwtClaims.builder()
                .email(claims.get("email", String.class))
                .role(claims.get("role", MemberRole.class))
                .id(claims.get("id", String.class))
                .build();
    }
}
