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

public interface FindPartnerRequestRepository extends JpaRepository<FindPartnerRequest, String>, JpaSpecificationExecutor<FindPartnerRequest> {

    @EntityGraph(value = "findPartnerRequest.all", type = EntityGraph.EntityGraphType.LOAD)
    List<FindPartnerRequest> getByClientId(final String clientId);

    @EntityGraph(value = "findPartnerRequest.all", type = EntityGraph.EntityGraphType.LOAD)
    FindPartnerRequest getById(final String id);

    @EntityGraph(value = "findPartnerRequest.all")
    Optional<FindPartnerRequest> findById(final String id);

    @Override
    @EntityGraph(value = "findPartnerRequest.all", type = EntityGraph.EntityGraphType.LOAD)
    Page<FindPartnerRequest> findAll(final Specification<FindPartnerRequest> specification, final Pageable pageable);

}
