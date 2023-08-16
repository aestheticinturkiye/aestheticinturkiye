package com.dev.esthomy.controller;

import com.dev.esthomy.converter.AccountConverter;
import com.dev.esthomy.dto.request.accountRequests.CreateAccountRequest;
import com.dev.esthomy.dto.response.CreateAccountResponse;
import com.dev.esthomy.models.Account;
import com.dev.esthomy.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    private final AccountConverter accountConverter;

    @PostMapping
    public ResponseEntity<CreateAccountResponse> create(final @Valid @RequestBody CreateAccountRequest request) {
        return ResponseEntity.ok().body(accountConverter.toResponse(accountService.create(request)));
    }

    @GetMapping
    public List<Account> getAllUsers(){
        return accountService.getAllUsers();
    }
}
