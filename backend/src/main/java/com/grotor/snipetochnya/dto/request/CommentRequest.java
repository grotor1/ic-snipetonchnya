package com.grotor.snipetochnya.dto.request;

import java.util.UUID;

public record CommentRequest(
        UUID postId,
        String content
) {
}
