package com.dev.esthomy.repository;

import com.dev.esthomy.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {

    Account findAccountByEmail(String email);

    //ayni ?
    Optional<Account> findByEmail(String email);
}
