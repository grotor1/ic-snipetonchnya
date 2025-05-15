package com.grotor.snipetochnya.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record PostResponse(
        UUID id,
        AccountResponse author,
        String title,
        String content,
        List<CommentResponse> comments,
        List<TechResponse> techs,
        List<TagResponse> tags,
        String language,
        LocalDateTime createdAt
) {
}