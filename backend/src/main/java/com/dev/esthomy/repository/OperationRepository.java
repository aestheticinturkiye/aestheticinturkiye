package com.dev.esthomy.repository;

import com.dev.esthomy.models.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation,String> {
}
