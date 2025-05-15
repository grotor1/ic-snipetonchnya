package com.grotor.snipetochnya.service;

import com.grotor.snipetochnya.dto.Role;
import com.grotor.snipetochnya.dto.request.AccountRequest;
import com.grotor.snipetochnya.dto.request.AuthenticationRequest;
import com.grotor.snipetochnya.dto.request.TokenRefreshRequest;
import com.grotor.snipetochnya.dto.response.AccountResponse;
import com.grotor.snipetochnya.dto.response.TokenCoupleResponse;
import com.grotor.snipetochnya.mapper.AccountMapper;
import com.grotor.snipetochnya.model.Account;
import com.grotor.snipetochnya.model.RefreshToken;
import com.grotor.snipetochnya.repository.AccountJpaRepository;
import com.grotor.snipetochnya.repository.RoleJpaRepository;
import com.grotor.snipetochnya.security.exception.AccountNotFoundException;
import com.grotor.snipetochnya.security.exception.AuthenticationHeaderException;
import com.grotor.snipetochnya.security.holder.UserContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.grotor.snipetochnya.util.SecurityConstants.ROLE;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final PasswordEncoder passwordEncoder;
    private final AccountJpaRepository accountJpaRepository;
    private final RoleJpaRepository roleJpaRepository;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final AccountMapper accountMapper;
    private final UserContextHolder userContextHolder;

    @Value("${security.login}")
    private String adminRootLogin;
    @Value("${security.password}")
    private String adminRootPassword;

    @Override
    public void registration(AccountRequest accountRequest) {
        String hashedPassword = passwordEncoder.encode(accountRequest.password());
        Account account = Account.builder()
                .login(accountRequest.login())
                .email(accountRequest.email())
                .fullName(accountRequest.fullName())
                .ghLogin(accountRequest.ghLogin())
                .password(hashedPassword)
                .role(roleJpaRepository.findByKey(Role.USER.name()).orElseThrow())
                .createdAt(LocalDateTime.now())
                .build();
        accountJpaRepository.save(account);
    }

    @Override
    public TokenCoupleResponse login(AuthenticationRequest authenticationRequest) {
        Account account = accountJpaRepository.findByLogin(authenticationRequest.login())
                .orElseThrow(() -> new AuthenticationHeaderException("Username not found"));

        if (authenticationRequest.login().equals(adminRootLogin) && authenticationRequest.password().equals(adminRootPassword)) {
            return new TokenCoupleResponse(
                    jwtService.generateToken(
                            account.getLogin(),
                            Collections.singletonMap(ROLE, account.getRole().getKey())
                    ),
                    refreshTokenService.createRefreshToken(authenticationRequest.login()).getToken()
            );
        }

        if (account.isBlocked()) {
            throw new AuthenticationHeaderException("You are blocked");
        }

        if (passwordEncoder.matches(authenticationRequest.password(), account.getPassword())) {
            return new TokenCoupleResponse(
                    jwtService.generateToken(
                            account.getLogin(),
                            Collections.singletonMap(ROLE, account.getRole().getKey())
                    ),
                    refreshTokenService.createRefreshToken(authenticationRequest.login()).getToken()
            );
        } else {
            throw new AuthenticationHeaderException("Wrong password");
        }
    }

    @Override
    public TokenCoupleResponse refresh(TokenRefreshRequest tokenRefreshRequest) {
        TokenCoupleResponse tokenCoupleResponse = refreshTokenService.findByToken(tokenRefreshRequest.refreshToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getAccount)
                .map(account -> new TokenCoupleResponse(
                                jwtService.generateToken(
                                        account.getLogin(),
                                        Collections.singletonMap(ROLE, account.getRole().getKey())
                                ),
                                refreshTokenService.createRefreshToken(account.getLogin()).getToken()
                        )
                )
                .orElseThrow(() -> new AuthenticationHeaderException("Wrong refresh token"));
        refreshTokenService.deleteRefreshToken(tokenRefreshRequest.refreshToken());
        return tokenCoupleResponse;
    }

    @Override
    public AccountResponse getUserInfoByToken(String token) {
        String login = jwtService.extractLogin(token);
        return accountMapper.toResponse(
                accountJpaRepository.findByLogin(login).orElseThrow(() -> new AuthenticationHeaderException("Username not found"))
        );
    }

    @Override
    public AccountResponse getCurrentUser() {
        String login = userContextHolder.getUserAccountFromSecurityContext().getLogin();
        return accountMapper.toResponse(
                accountJpaRepository.findByLogin(login).orElseThrow(() -> new AuthenticationHeaderException("Username not found"))
        );
    }

    @Override
    public List<AccountResponse> getAll() {
        log.info(accountJpaRepository.findAll().stream().map(Account::isBlocked).toList().toString());
        return accountJpaRepository.findAll().stream().map(accountMapper::toResponse).toList();
    }

    @Override
    public AccountResponse getById(UUID id) {
        return accountJpaRepository.findById(id).map(accountMapper::toResponse).orElseThrow(() -> new AccountNotFoundException(id));
    }

    @Override
    public AccountResponse toggleBlock(UUID id) {
        Account account = accountJpaRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
        account.setBlocked(!account.isBlocked());
        log.info(String.valueOf(account.isBlocked()));
        accountJpaRepository.save(account);
        return accountMapper.toResponse(account);
    }

    @Override
    public AccountResponse update(AccountRequest accountRequest, UUID id) {
        Account account = accountJpaRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
        account.setLogin(accountRequest.login() != null ? accountRequest.login() : account.getLogin());
        account.setEmail(accountRequest.email() != null ? accountRequest.email() : account.getEmail());
        account.setFullName(accountRequest.fullName() != null ? accountRequest.fullName() : account.getFullName());
        accountJpaRepository.save(account);
        return accountMapper.toResponse(account);
    }

    @Override
    public void delete(UUID id) {
        accountJpaRepository.delete(accountJpaRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id)));
    }
}
