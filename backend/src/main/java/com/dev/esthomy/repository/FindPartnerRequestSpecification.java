package com.dev.esthomy.repository;

import com.dev.esthomy.models.FindPartnerRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class FindPartnerRequestSpecification {
    public Specification<FindPartnerRequest> getFindPartnerRequestSpecification() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), "PENDING");
    }
}
