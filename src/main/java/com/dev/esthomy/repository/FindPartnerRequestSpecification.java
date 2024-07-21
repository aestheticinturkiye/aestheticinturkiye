package com.dev.esthomy.repository;

import com.dev.esthomy.models.FindPartnerRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FindPartnerRequestSpecification {

    public Specification<FindPartnerRequest> getClientFindPartnerRequestSpecification(final UUID clientId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("client").get("id"), clientId);
    }
}
