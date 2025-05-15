package com.grotor.snipetochnya.security.exception;

import java.util.UUID;

public class PostNotFoundException extends NotFoundException {
    public PostNotFoundException(UUID id) {
        super("Post not found with id = %s".formatted(id));
    }
}
