package com.grotor.snipetochnya.security.exception;

import java.util.UUID;

public class CommentNotFoundException extends NotFoundException {
    public CommentNotFoundException(UUID id) {
        super("Comment not found with id = %s".formatted(id));
    }
}
