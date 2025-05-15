package com.grotor.snipetochnya.repository;

import com.grotor.snipetochnya.model.Search;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SearchJpaRepository extends JpaRepository<Search, UUID> {
}
