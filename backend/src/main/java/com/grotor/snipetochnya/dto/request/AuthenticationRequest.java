package com.grotor.snipetochnya.dto.request;

public record AuthenticationRequest (
        String login,
        String password
) {
}
