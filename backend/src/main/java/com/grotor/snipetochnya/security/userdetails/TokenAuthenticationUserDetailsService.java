package com.grotor.snipetochnya.security.userdetails;


import com.grotor.snipetochnya.dto.response.AccountResponse;
import com.grotor.snipetochnya.security.exception.AuthenticationHeaderException;
import com.grotor.snipetochnya.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class TokenAuthenticationUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {
    AccountService accountService;

    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken preAuthenticatedAuthenticationToken) {
        return loadUserDetails((AccountResponse) preAuthenticatedAuthenticationToken.getPrincipal(), String.valueOf(preAuthenticatedAuthenticationToken.getCredentials()));
    }

    private UserDetails loadUserDetails(AccountResponse accountResponse, String token) {
        log.info("Loading user details for {}", accountResponse);
        try {
            List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(accountResponse.role().name()));
            return Optional.ofNullable(accountResponse)
                    .map(account -> UserAccount.builder()
                            .id(account.id())
                            .login(account.login())
                            .fullName(account.fullName())
                            .email(account.email())
                            .authorities(authorities)
                            .isAccountNonExpired(true)
                            .isCredentialsNonExpired(true)
                            .isAccountNonLocked(true)
                            .isEnabled(!account.blocked())
                            .token(token)
                            .build())
                    .orElseThrow(() -> new UsernameNotFoundException("Unknown user by token %s".formatted(token)));
        } catch (Exception exception) {
            throw new AuthenticationHeaderException(exception.getMessage());
        }
    }
}
