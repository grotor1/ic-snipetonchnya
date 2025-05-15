package com.grotor.snipetochnya.service;

import com.grotor.snipetochnya.model.RefreshToken;
import com.grotor.snipetochnya.repository.AccountJpaRepository;
import com.grotor.snipetochnya.repository.RefreshTokenJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final RefreshTokenJpaRepository refreshTokenRepository;
    private final AccountJpaRepository accountRepository;

    @Value("${jwt.expiration.refresh.mills}")
    private long expirationRefreshInMills;

    @Override
    public RefreshToken createRefreshToken(String login){
        RefreshToken refreshToken = RefreshToken.builder()
                .account(accountRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("Login not found")))
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(expirationRefreshInMills))
                .build();
        return refreshTokenRepository.save(refreshToken);
    }


    @Override
    public Optional<RefreshToken> findByToken(String token){
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token){
        if(token.getExpiryDate().compareTo(Instant.now())<0){
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new login..!");
        }
        return token;
    }

    @Override
    public void deleteRefreshToken(String token) {
        refreshTokenRepository.findByToken(token).ifPresent(refreshTokenRepository::delete);
    }
}
