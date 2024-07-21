package com.dev.esthomy.repository;

import com.dev.esthomy.models.Proposal;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProposalRepository extends JpaRepository<Proposal, String> {
    List<Proposal> getByBrokerId(final String brokerId);

    List<Proposal> getByClientId(final String clientId);

    @Override
    @EntityGraph(value = "proposal.all", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Proposal> findById(final String id);
}
