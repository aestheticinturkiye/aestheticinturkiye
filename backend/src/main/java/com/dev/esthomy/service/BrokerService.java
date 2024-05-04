package com.dev.esthomy.service;

import com.dev.esthomy.dto.BrokerDto;
import com.dev.esthomy.models.Broker;
import com.dev.esthomy.repository.BrokerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrokerService {
    private final BrokerRepository brokerRepository;

    public BrokerDto getByEmail(final String email) {
        final Optional<Broker> optionalBroker = brokerRepository.findByEmail(email);
        return optionalBroker.map(b -> BrokerDto.builder()
                .id(b.getId())
                .phone(b.getPhone())
                .name(b.getName())
                .surname(b.getSurname())
                .email(b.getEmail())
                .build()).orElseThrow(() -> new RuntimeException("Broker not found"));
    }

    public BrokerDto getById(final String id) {
        final Optional<Broker> optionalBroker = brokerRepository.findById(id);
        return optionalBroker.map(b -> BrokerDto.builder()
                .id(b.getId())
                .phone(b.getPhone())
                .name(b.getName())
                .surname(b.getSurname())
                .email(b.getEmail())
                .build()).orElseThrow(() -> new RuntimeException("Broker not found"));
    }
}
