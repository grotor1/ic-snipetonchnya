package com.grotor.snipetochnya.security.exception;

import java.util.UUID;

public class AccountNotFoundException extends NotFoundException {
    public AccountNotFoundException(UUID id) {
        super("Account not found with id = %s".formatted(id));
    }
}
