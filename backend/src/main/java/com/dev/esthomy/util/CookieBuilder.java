package com.dev.esthomy.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.HttpCookie;

@Component
public class CookieBuilder {
    @Value("${cookie.accessTokenCookieName}")
    private String accessTokenCookieName;

    @Value("${cookie.refreshTokenCookieName}")
    private String refreshTokenCookieName;

    public HttpCookie buildAccessTokenCookie(String value, Long maxAge) {
        HttpCookie cookie = new HttpCookie(accessTokenCookieName, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        return cookie;
    }

    public HttpCookie buildRefreshTokenCookie( String value, Long maxAge) {
        HttpCookie cookie = new HttpCookie(refreshTokenCookieName, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        return cookie;
    }
}
