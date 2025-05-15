package com.grotor.snipetochnya.repository;

import com.grotor.snipetochnya.model.Tech;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TechJpaRepository extends JpaRepository<Tech, UUID> {
}

