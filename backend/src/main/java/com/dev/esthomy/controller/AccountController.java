package com.dev.esthomy.controller;

import com.dev.esthomy.dto.request.GetAccountRequest;
import com.dev.esthomy.dto.response.GetAccountResponse;
import com.dev.esthomy.jwt.model.JwtClaims;
import com.dev.esthomy.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/account")
@RequiredArgsConstructor
@Slf4j
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<GetAccountResponse> getAccount(@AuthenticationPrincipal JwtClaims principal,
                                                         @RequestBody @Valid GetAccountRequest accountRequest) {
        log.info(String.valueOf(accountRequest));
        return ResponseEntity.ok().body(accountService.getSignedAccount(principal, accountRequest));
    }
}
