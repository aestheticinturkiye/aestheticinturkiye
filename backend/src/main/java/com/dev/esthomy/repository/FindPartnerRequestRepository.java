package com.dev.esthomy.repository;

import com.dev.esthomy.models.FindPartnerRequest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FindPartnerRequestRepository extends JpaRepository<FindPartnerRequest, String> {

    @EntityGraph(value = "FindPartnerRequest.proposals")
    List<FindPartnerRequest> getByClientId(final String clientId);

    @Override
    @EntityGraph(value = "FindPartnerRequest.proposals")
    FindPartnerRequest getById(final String id);

}
