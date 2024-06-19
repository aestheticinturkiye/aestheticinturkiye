package com.dev.esthomy.service;

import com.dev.esthomy.dto.BrokerDto;
import com.dev.esthomy.dto.CredentialDto;
import com.dev.esthomy.dto.request.CreateBrokerRequest;
import com.dev.esthomy.dto.request.GetBrokerResponse;
import com.dev.esthomy.dto.response.CreateBrokerResponse;
import com.dev.esthomy.exception.BusinessException;
import com.dev.esthomy.jwt.model.JwtClaims;
import com.dev.esthomy.models.Broker;
import com.dev.esthomy.models.enums.MemberRole;
import com.dev.esthomy.repository.BrokerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrokerService {
    private final BrokerRepository brokerRepository;
    private final CredentialService credentialservice;

    public CreateBrokerResponse create(final CreateBrokerRequest request) {
        final Broker broker = brokerRepository.save(Broker.builder()
                .age(request.getAge())
                .country(request.getCountry())
                .gender(request.getGender())
                .phone(request.getPhone())
                .surname(request.getSurname())
                .password(request.getPassword())
                .isActive(true)
                .email(request.getEmail())
                .name(request.getName()).build());

        final CredentialDto credentialDto = CredentialDto.builder()
                .email(broker.getEmail())
                .password(broker.getPassword())
                .role(MemberRole.BROKER).build();


        credentialservice.createCredential(credentialDto);
        return CreateBrokerResponse.builder()
                .id(broker.getId())
                .name(broker.getName())
                .email(broker.getEmail()).build();
    }

        public BrokerDto getByEmail ( final String email){
            final Optional<Broker> optionalBroker = brokerRepository.findByEmail(email);
            return optionalBroker.map(b -> BrokerDto.builder()
                    .id(b.getId())
                    .phone(b.getPhone())
                    .name(b.getName())
                    .surname(b.getSurname())
                    .email(b.getEmail())
                    .build()).orElseThrow(() -> new RuntimeException("Broker not found"));
        }

        public BrokerDto getById ( final String id){
            final Optional<Broker> optionalBroker = brokerRepository.findById(id);
            return optionalBroker.map(b -> BrokerDto.builder()
                    .id(b.getId())
                    .phone(b.getPhone())
                    .name(b.getName())
                    .surname(b.getSurname())
                    .email(b.getEmail())
                    .build()).orElseThrow(() -> new RuntimeException("Broker not found"));
        }

    public GetBrokerResponse getBroker(final JwtClaims principal) {
        final Broker broker = brokerRepository.findById(principal.getId()).orElseThrow(() -> new BusinessException("Broker not found"));
        return GetBrokerResponse.builder()
                .broker(BrokerDto.toDto(broker))
                .build();
    }
}
