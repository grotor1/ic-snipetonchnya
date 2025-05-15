package com.grotor.snipetochnya.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "role")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Role extends AbstractEntity {
    private String key;
    private String label;
}
