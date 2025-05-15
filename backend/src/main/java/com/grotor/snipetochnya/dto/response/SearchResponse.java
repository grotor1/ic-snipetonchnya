package com.grotor.snipetochnya.dto.response;

import com.grotor.snipetochnya.dto.request.TagRequest;
import com.grotor.snipetochnya.dto.request.TechRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record SearchResponse(
        UUID id,
        String titleEntry,
        String name,
        List<TechResponse> techs,
        List<TagResponse> tags,
        LocalDateTime createdAt
) {
}
