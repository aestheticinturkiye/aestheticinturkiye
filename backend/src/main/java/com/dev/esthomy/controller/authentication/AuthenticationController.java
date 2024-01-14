package com.dev.esthomy.controller.authentication;

import com.dev.esthomy.dto.authentication.request.LoginRequest;
import com.dev.esthomy.dto.authentication.response.LoginResponse;
import com.dev.esthomy.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/user")
@RequiredArgsConstructor
public class AuthenticationController {
    private final LoginService loginService;
    @GetMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody final LoginRequest loginRequest){
        return ResponseEntity.ok().body(loginService.login(loginRequest));
    }
}
