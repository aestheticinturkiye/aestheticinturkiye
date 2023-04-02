package com.dev.esthomy.repository;

import com.dev.esthomy.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
