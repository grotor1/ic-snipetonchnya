package com.grotor.snipetochnya.dto.request;

import com.grotor.snipetochnya.model.Account;
import liquibase.pro.packaged.S;

import java.util.List;
import java.util.UUID;

public record PostRequest(
        String title,
        String content,
        List<UUID> techs,
        List<UUID> tags,
        String language
) {
}
