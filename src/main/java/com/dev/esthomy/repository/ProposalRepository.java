package com.dev.esthomy.repository;

import com.dev.esthomy.models.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProposalRepository extends JpaRepository<Proposal, String> {
    List<Proposal> getByBrokerId(final String brokerId);
    List<Proposal> getByClientId(final String clientId);
}
