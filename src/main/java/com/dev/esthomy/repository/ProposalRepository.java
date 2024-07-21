package com.dev.esthomy.repository;

import com.dev.esthomy.models.Proposal;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProposalRepository extends JpaRepository<Proposal, UUID> {
    List<Proposal> getByBrokerId(final UUID brokerId);

    List<Proposal> getByClientId(final UUID id);

    @Override
    @EntityGraph(value = "proposal.all", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Proposal> findById(final UUID id);
}
