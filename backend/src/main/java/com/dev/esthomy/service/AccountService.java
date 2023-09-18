package com.dev.esthomy.service;

import com.dev.esthomy.converter.AccountConverter;
import com.dev.esthomy.dto.AccountDto;
import com.dev.esthomy.dto.request.accountRequests.CreateAccountRequest;
import com.dev.esthomy.dto.response.CreateAccountResponse;
import com.dev.esthomy.models.Account;
import com.dev.esthomy.repository.AccountRepository;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountConverter accountConverter;
    private final FileUploader fileUploader;

    public AccountDto create(CreateAccountRequest request) {
        return accountConverter.toDto(accountRepository.save(accountConverter.toModel(request)));
    }

    public CreateAccountResponse createCreateResponse(CreateAccountRequest request) {
        return accountConverter.toResponse(this.create(request));
    }

    public List<AccountDto> getAllUsers() {
        return accountConverter.toList(accountRepository.findAll());
    }

    public Account getUserByMail(String email) {
        return accountRepository.findAccountByEmail(email);
    }

    public void uploadFile(String email,File file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        try {
            Account account = this.getUserByMail(email);
            if(account!= null)
            {
                fileUploader.uploadToMinio(account,file);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public List<String> listBucketObjects(String email) {
        try {
            Account account = this.getUserByMail(email);
            if(account!= null)
            {
                return fileUploader.listObjectFiles(account);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return null;
    }

}
