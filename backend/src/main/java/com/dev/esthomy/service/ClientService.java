package com.dev.esthomy.service;

import com.dev.esthomy.constants.EmailTemplates;
import com.dev.esthomy.dto.ClientDto;
import com.dev.esthomy.dto.CredentialDto;
import com.dev.esthomy.dto.request.CreateClientRequest;
import com.dev.esthomy.dto.response.CreateClientResponse;
import com.dev.esthomy.dto.response.GetClientResponse;
import com.dev.esthomy.exception.BusinessException;
import com.dev.esthomy.jwt.model.JwtClaims;
import com.dev.esthomy.models.Client;
import com.dev.esthomy.models.enums.MemberRole;
import com.dev.esthomy.repository.ClientRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {
    private final ClientRepository clientRepository;
    private final CredentialService credentialService;
    private final MailService mailService;

    public CreateClientResponse create(final CreateClientRequest request) {
        final Client client = clientRepository.save(Client.builder()
                .age(request.getAge())
                .country(request.getCountry())
                .gender(request.getGender())
                .phone(request.getPhone())
                .surname(request.getSurname())
                .password(request.getPassword())
                .isActive(true)
                .email(request.getEmail())
                .name(request.getName())
                .build());

        final CredentialDto credentialDto = CredentialDto.builder()
                .email(client.getEmail())
                .password(client.getPassword())
                .role(MemberRole.CLIENT)
                .build();


        credentialService.createCredential(credentialDto);

        try {
            mailService.sendEmail(request.getEmail(),request.getName(),null, EmailTemplates.WELCOME_MESSAGE);
        } catch (MessagingException | UnsupportedEncodingException e) {
            log.error("Failed to send email. Error: " + e.getMessage());
        }

        return CreateClientResponse.builder()
                .name(client.getName())
                .email(client.getEmail())
                .build();
    }

    public ClientDto getByEmail(final String email) {
        final Optional<Client> optionalClient = clientRepository.findByEmail(email);
        return optionalClient.map(b -> ClientDto.builder()
                .id(b.getId())
                .phone(b.getPhone())
                .name(b.getName())
                .surname(b.getSurname())
                .email(b.getEmail())
                .age(b.getAge())
                .build()).orElseThrow(() -> new BusinessException("Client not found"));
    }

    public ClientDto getById(final String id) {
        final Optional<Client> optionalClient = clientRepository.findById(id);
        return optionalClient.map(b -> ClientDto.builder()
                .id(b.getId())
                .phone(b.getPhone())
                .name(b.getName())
                .surname(b.getSurname())
                .email(b.getEmail())
                .age(b.getAge())
                .build()).orElseThrow(() -> new BusinessException("Client not found"));
    }

    public GetClientResponse getClient(final JwtClaims principal) {
        final Client client = clientRepository.findById(principal.getId()).orElseThrow(() -> new BusinessException("Client not found"));
        return GetClientResponse.builder()
                .client(ClientDto.toDto(client))
                .build();
    }
}
