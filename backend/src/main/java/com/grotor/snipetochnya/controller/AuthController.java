package com.grotor.snipetochnya.controller;

import com.grotor.snipetochnya.api.AuthApi;
import com.grotor.snipetochnya.dto.request.AccountRequest;
import com.grotor.snipetochnya.dto.request.AuthenticationRequest;
import com.grotor.snipetochnya.dto.request.TokenRefreshRequest;
import com.grotor.snipetochnya.dto.response.AccountResponse;
import com.grotor.snipetochnya.dto.response.TokenCoupleResponse;
import com.grotor.snipetochnya.security.holder.BaseUserContextHolder;
import com.grotor.snipetochnya.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController implements AuthApi {
    private final AccountService accountService;

    @PostMapping("/registration")
    public void registration(@RequestBody AccountRequest accountRequest) {
        accountService.registration(accountRequest);
    }

    @PostMapping("/login")
    public TokenCoupleResponse login(@RequestBody AuthenticationRequest authenticationRequest) {
        return accountService.login(authenticationRequest);
    }

    @PostMapping("/refresh")
    public TokenCoupleResponse refresh(@RequestBody TokenRefreshRequest tokenRefreshRequest) {
        return accountService.refresh(tokenRefreshRequest);
    }
}
