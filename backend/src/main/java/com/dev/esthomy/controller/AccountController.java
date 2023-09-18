package com.dev.esthomy.controller;

import com.dev.esthomy.converter.AccountConverter;
import com.dev.esthomy.dto.AccountDto;
import com.dev.esthomy.dto.request.MinioRequests.UploadObjectToMinio;
import com.dev.esthomy.dto.request.accountRequests.CreateAccountRequest;
import com.dev.esthomy.dto.response.CreateAccountResponse;
import com.dev.esthomy.service.AccountService;
import io.minio.errors.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;


@RestController
@RequestMapping("/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    private final AccountConverter accountConverter;

    @PostMapping
    public ResponseEntity<CreateAccountResponse> create(final @Valid @RequestBody CreateAccountRequest request) {
        return ResponseEntity.ok().body(accountService.createCreateResponse(request));
    }

    @GetMapping
    public List<AccountDto> getAllUsers(){
        return accountService.getAllUsers();
    }


    @PostMapping("/upload")
    public ResponseEntity<String> uploadToMinio(final @RequestBody UploadObjectToMinio request) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        //toDo: refactor.
        File file = new File(request.getFile());
        accountService.uploadFile(request.getEmail(),file);
         return ResponseEntity.ok().body("Executed");
    }

    @PostMapping("/listObjects")
    public ResponseEntity<List<String>> listObjects(final @RequestBody String email) {
        return ResponseEntity.ok().body(accountService.listBucketObjects(email));
    }
}
