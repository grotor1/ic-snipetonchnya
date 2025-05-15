package com.grotor.snipetochnya.security.exception;

import java.util.UUID;

public class TagNotFoundException extends NotFoundException {
    public TagNotFoundException(UUID id) {
        super("Tag not found with id = %s".formatted(id));
    }
}
