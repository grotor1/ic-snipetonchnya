package com.grotor.snipetochnya.security.exception;

import java.util.UUID;

public class TechNotFoundException extends NotFoundException {
    public TechNotFoundException(UUID id) {
        super("Tech not found with id = %s".formatted(id));
    }
}
