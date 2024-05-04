package com.dev.esthomy.service;

import com.dev.esthomy.dto.ClientDto;
import com.dev.esthomy.dto.CredentialDto;
import com.dev.esthomy.dto.request.CreateClientRequest;
import com.dev.esthomy.dto.response.CreateClientResponse;
import com.dev.esthomy.models.Client;
import com.dev.esthomy.models.enums.MemberRole;
import com.dev.esthomy.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final CredentialService credentialService;

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
                .build()).orElseThrow(() -> new RuntimeException("Client not found"));
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
                .build()).orElseThrow(() -> new RuntimeException("Client not found"));
    }

}
