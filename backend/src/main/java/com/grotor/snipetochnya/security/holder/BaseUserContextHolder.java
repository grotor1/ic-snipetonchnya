package com.grotor.snipetochnya.security.holder;

import com.grotor.snipetochnya.dto.response.AccountResponse;
import com.grotor.snipetochnya.security.userdetails.UserAccount;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class BaseUserContextHolder implements UserContextHolder {
    public UserAccount getUserAccountFromSecurityContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof PreAuthenticatedAuthenticationToken preAuthenticatedAuthenticationToken) {
            Object principal = preAuthenticatedAuthenticationToken.getPrincipal();
            System.out.println(principal);
            if (principal instanceof UserAccount) {
                return (UserAccount) principal;
            }
        }

        throw new UsernameNotFoundException("Not found user context");
    }
}
