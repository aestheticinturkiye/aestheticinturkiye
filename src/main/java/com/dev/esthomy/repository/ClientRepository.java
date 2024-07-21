package com.dev.esthomy.repository;

import com.dev.esthomy.models.Client;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByEmail(final String email);

    @Override
    @EntityGraph(value = "client.findPartnerRequests", type = EntityGraph.EntityGraphType.LOAD, attributePaths = {"findPartnerRequests"})
    Optional<Client> findById(final String id);
}
