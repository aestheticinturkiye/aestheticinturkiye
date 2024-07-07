package com.dev.esthomy.repository;

import com.dev.esthomy.models.FindPartnerRequest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface FindPartnerRequestRepository extends JpaRepository<FindPartnerRequest, String>, JpaSpecificationExecutor<FindPartnerRequest> {

    @EntityGraph(value = "FindPartnerRequest.proposals", type = EntityGraph.EntityGraphType.LOAD)
    List<FindPartnerRequest> getByClientId(final String clientId);

    @Override
    @EntityGraph(value = "FindPartnerRequest.proposals", type = EntityGraph.EntityGraphType.LOAD)
    FindPartnerRequest getById(final String id);

    @Override
    @EntityGraph(value = "FindPartnerRequest.proposals", type = EntityGraph.EntityGraphType.LOAD)
    Optional<FindPartnerRequest> findById(String id);

}
