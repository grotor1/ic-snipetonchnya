package com.grotor.snipetochnya.service;

import com.grotor.snipetochnya.model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(String login);

    Optional<RefreshToken> findByToken(String token);

    RefreshToken verifyExpiration(RefreshToken token);

    void deleteRefreshToken(String token);
}
