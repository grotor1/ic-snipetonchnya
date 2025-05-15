package com.grotor.snipetochnya.repository;

import com.grotor.snipetochnya.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TagJpaRepository extends JpaRepository<Tag, UUID> {
}
