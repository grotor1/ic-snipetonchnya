package com.grotor.snipetochnya.dto.request;

import java.util.List;
import java.util.UUID;

public record SearchRequest (
        String name,
        String titleEntry,
        List<UUID> techs,
        List<UUID> tags
) {
}
