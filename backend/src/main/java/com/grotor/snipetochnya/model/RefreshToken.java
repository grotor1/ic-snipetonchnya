package com.grotor.snipetochnya.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Entity
@Table(name="refresh_token")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RefreshToken extends AbstractEntity{

    private String token;
    @Column(name = "expiry_date")
    private Instant expiryDate;
    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;
}