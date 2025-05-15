package com.grotor.snipetochnya.dto.request;

public record AccountRequest (
        String login,
        String password,
        String email,
        String fullName,
        String ghLogin
) {
}
