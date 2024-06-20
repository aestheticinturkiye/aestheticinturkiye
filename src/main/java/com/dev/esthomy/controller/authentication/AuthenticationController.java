package com.dev.esthomy.controller.authentication;

import com.dev.esthomy.dto.authentication.request.AuthenticateWithRefreshTokenResponse;
import com.dev.esthomy.dto.authentication.request.LoginRequest;
import com.dev.esthomy.dto.authentication.response.LoginResponse;
import com.dev.esthomy.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final LoginService loginService;
    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody final LoginRequest loginRequest){
        return loginService.login(loginRequest);
    }

    @PostMapping("authenticateWithRefreshToken")
    public ResponseEntity<AuthenticateWithRefreshTokenResponse> authenticateWithRefreshToken(@CookieValue("REFRESH_TOKEN") final String refreshToken) {
        return loginService.authenticateWithRefreshToken(refreshToken);
    }
}
