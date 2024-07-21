package com.dev.esthomy.repository;

import com.dev.esthomy.models.Client;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    Optional<Client> findByEmail(final String email);

    @Override
    @EntityGraph(value = "client.findPartnerRequests", type = EntityGraph.EntityGraphType.LOAD, attributePaths = {"findPartnerRequests"})
    Optional<Client> findById(final UUID id);
}
