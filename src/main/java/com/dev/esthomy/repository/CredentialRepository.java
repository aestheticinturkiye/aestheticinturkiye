package com.dev.esthomy.repository;

import com.dev.esthomy.models.Credential;
import com.dev.esthomy.models.enums.CredentialStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface CredentialRepository extends JpaRepository<Credential, UUID> {
    Optional<Credential> findCredentialByEmailAndStatus(final String email, final CredentialStatus status);
}
