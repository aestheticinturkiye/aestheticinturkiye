package com.dev.esthomy.service;

import com.dev.esthomy.dto.CredentialDto;
import com.dev.esthomy.dto.authentication.request.LoginRequest;
import com.dev.esthomy.models.Credential;
import com.dev.esthomy.models.enums.CredentialStatus;
import com.dev.esthomy.repository.CredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CredentialService {

    private final CredentialRepository credentialRepository;

    public CredentialDto checkCredentialOptional(final LoginRequest request) {
        final CredentialDto credentialDto = credentialRepository.findCredentialByEmailAndStatus(request.getEmailAddress(), CredentialStatus.ACTIVE)
                .map(c -> CredentialDto.builder()
                        .email(c.getEmail())
                        .password(c.getPassword())
                        .role(c.getMemberRole())
                        .build()).orElseThrow(() -> new RuntimeException("Credential Not Found."));

        if (!checkPassword(credentialDto, request)) {
            throw new RuntimeException("Incorrect password.");
        }

        return credentialDto;
    }

    public void createCredential(final CredentialDto credentialDto) {
        credentialRepository.save(Credential.builder()
                .email(credentialDto.getEmail())
                .password(credentialDto.getPassword())
                .memberRole(credentialDto.getRole())
                .status(CredentialStatus.ACTIVE)
                .build());
    }

    private boolean checkPassword(final CredentialDto credentialDto, final LoginRequest request) {
        return request.getPassword().equals(credentialDto.getPassword());
    }
}
