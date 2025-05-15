package com.grotor.snipetochnya.security.filter;

import com.grotor.snipetochnya.dto.response.AccountResponse;
import com.grotor.snipetochnya.service.AccountService;
import com.grotor.snipetochnya.util.HttpResponseUtil;
import com.grotor.snipetochnya.util.HttpSettingUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
public class TokenAuthenticationFilter extends RequestHeaderAuthenticationFilter {
    private final AccountService accountService;

    public TokenAuthenticationFilter(AccountService accountService, AuthenticationManager authenticationManager) {
        this.accountService = accountService;
        this.setAuthenticationManager(authenticationManager);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {
        String requestURI = ((HttpServletRequest) request).getRequestURI();

        try {
            String token = HttpSettingUtil.getTokenFromValidatedAuthorizationHeader(((HttpServletRequest) request).getHeader(AUTHORIZATION));
            if (Objects.nonNull(token)) {
                AccountResponse accountResponse = accountService.getUserInfoByToken(token);
                SecurityContextHolder.getContext().setAuthentication(new PreAuthenticatedAuthenticationToken(accountResponse, token));
            }
            chain.doFilter(request, response);
        } catch (Exception exception) {
            HttpResponseUtil.putExceptionInResponse(((HttpServletRequest) request), ((HttpServletResponse) response),
                    exception, HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

}
