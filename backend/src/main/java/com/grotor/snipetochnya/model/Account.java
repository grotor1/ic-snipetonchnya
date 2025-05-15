package com.grotor.snipetochnya.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "account")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Account extends AbstractEntity {
    private String login;
    private String password;
    private String email;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "gh_login")
    private String ghLogin;

    @Column(name = "is_blocked")
    private boolean blocked;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "role")
    private Role role;
}
