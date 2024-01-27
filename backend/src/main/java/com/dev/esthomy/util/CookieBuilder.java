package com.dev.esthomy.util;

import com.dev.esthomy.jwt.model.Token;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.HttpCookie;

@Component
public class CookieBuilder {
    @Value("${cookie.accessTokenCookieName}")
    private String accessTokenCookieName;

    @Value("${cookie.refreshTokenCookieName}")
    private String refreshTokenCookieName;

    public HttpCookie buildAccessTokenCookie(final Token token) {
        HttpCookie cookie = new HttpCookie(accessTokenCookieName, token.getValue());
        cookie.setMaxAge(token.getDuration());
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        return cookie;
    }

    public HttpCookie buildRefreshTokenCookie(final Token token) {
        HttpCookie cookie = new HttpCookie(refreshTokenCookieName, token.getValue());
        cookie.setMaxAge(token.getDuration());
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        return cookie;
    }
}
