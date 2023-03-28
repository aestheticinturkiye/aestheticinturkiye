package com.dev.esthomy.repository;

import com.dev.esthomy.models.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, String>{
}
