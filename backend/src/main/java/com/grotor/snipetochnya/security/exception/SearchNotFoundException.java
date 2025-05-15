package com.grotor.snipetochnya.security.exception;

import java.util.UUID;

public class SearchNotFoundException extends NotFoundException {
    public SearchNotFoundException(UUID id) {
        super("Search not found with id = %s".formatted(id));
    }
}
