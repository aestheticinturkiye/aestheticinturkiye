package com.dev.esthomy.repository;

import com.dev.esthomy.models.FindPartnerRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class FindPartnerRequestSpecification {

    public Specification<FindPartnerRequest> getClientFindPartnerRequestSpecification(final String clientId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("clientId"), clientId);
    }
}
