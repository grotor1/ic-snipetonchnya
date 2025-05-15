package com.grotor.snipetochnya.controller;

import com.grotor.snipetochnya.dto.response.AccountResponse;
import com.grotor.snipetochnya.security.holder.BaseUserContextHolder;
import com.grotor.snipetochnya.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/me")
    public AccountResponse me() {
        return accountService.getCurrentUser();
    }

    @GetMapping("/{id}")
    public AccountResponse get(@PathVariable UUID id) {
        return accountService.getById(id);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<AccountResponse> allUsers() {
        return accountService.getAll();
    }

    @PostMapping("/{id}/toggle-block")
    @PreAuthorize("hasAuthority('ADMIN')")
    public AccountResponse block(@PathVariable UUID id) {
        return accountService.toggleBlock(id);
    }
}
