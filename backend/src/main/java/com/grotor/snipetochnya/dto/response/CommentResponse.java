package com.grotor.snipetochnya.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record CommentResponse(
        UUID id,
        AccountResponse author,
        String content,
        LocalDateTime createdAt
) {
}
