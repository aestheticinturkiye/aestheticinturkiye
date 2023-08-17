package com.dev.esthomy.repository;

import com.dev.esthomy.models.OperationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRequestRepository extends JpaRepository<OperationRequest, String> {
    List<OperationRequest> findAllByUserAccountId(String accountId);
}
