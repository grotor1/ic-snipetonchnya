package com.grotor.snipetochnya.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record TagResponse(
        UUID id,
        String label,
        LocalDateTime createdAt
) {
}
