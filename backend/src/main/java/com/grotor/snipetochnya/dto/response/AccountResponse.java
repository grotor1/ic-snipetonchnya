package com.grotor.snipetochnya.dto.response;

import com.grotor.snipetochnya.dto.Role;

import java.time.LocalDateTime;
import java.util.UUID;

public record AccountResponse(
        UUID id,
        String login,
        String email,
        String fullName,
        String ghLogin,
        Role role,
        boolean blocked,
        LocalDateTime createdAt
) {
}
