package com.dev.esthomy.repository;

import com.dev.esthomy.models.Broker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BrokerRepository extends JpaRepository<Broker, UUID> {
    Optional<Broker> findByEmail(final String email);
}
