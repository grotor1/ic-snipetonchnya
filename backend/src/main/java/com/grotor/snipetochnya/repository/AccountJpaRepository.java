package com.grotor.snipetochnya.repository;

import com.grotor.snipetochnya.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountJpaRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findByLogin(String login);
}
