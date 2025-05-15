package com.grotor.snipetochnya.dto.response;

import java.util.List;

public record Pagination<T>(
        List<T> items,
        int page,
        int total,
        int size
) {
}
