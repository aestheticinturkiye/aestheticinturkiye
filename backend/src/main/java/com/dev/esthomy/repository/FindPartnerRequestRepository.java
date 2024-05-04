package com.dev.esthomy.repository;

import com.dev.esthomy.models.FindPartnerRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FindPartnerRequestRepository extends JpaRepository<FindPartnerRequest, String> {
    List<FindPartnerRequest> getByClientId(final String clientId);
}
