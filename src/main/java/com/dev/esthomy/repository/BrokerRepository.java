package com.dev.esthomy.repository;

import com.dev.esthomy.models.Broker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrokerRepository extends JpaRepository<Broker, String> {
    Optional<Broker> findByEmail(final String email);
}
