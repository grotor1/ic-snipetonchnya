package com.grotor.snipetochnya.dto.response;

public record TokenCoupleResponse(
    String accessToken,
    String refreshToken
) {
}
