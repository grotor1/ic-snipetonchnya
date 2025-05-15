package com.grotor.snipetochnya.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "tech")
@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Tech extends AbstractEntity {
    @Column(name = "label", nullable = false, unique = true)
    private String label;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}

