package com.grotor.snipetochnya.repository;

import com.grotor.snipetochnya.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleJpaRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByKey(String key);
}
