package com.dev.esthomy.repository;

import com.dev.esthomy.models.FindPartnerRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FindPartnerRequestRepository extends JpaRepository<FindPartnerRequest, UUID>, JpaSpecificationExecutor<FindPartnerRequest> {

    @EntityGraph(value = "findPartnerRequest.all", type = EntityGraph.EntityGraphType.LOAD)
    List<FindPartnerRequest> getByClientId(final UUID clientId);

    @EntityGraph(value = "findPartnerRequest.all", type = EntityGraph.EntityGraphType.LOAD)
    FindPartnerRequest getById(final UUID id);

    @EntityGraph(value = "findPartnerRequest.all")
    Optional<FindPartnerRequest> findById(final UUID id);

    @Override
    @EntityGraph(value = "findPartnerRequest.all", type = EntityGraph.EntityGraphType.LOAD)
    Page<FindPartnerRequest> findAll(final Specification<FindPartnerRequest> specification, final Pageable pageable);

}
