package com.grotor.snipetochnya.util;


import com.grotor.snipetochnya.security.exception.AuthenticationHeaderException;
import liquibase.repackaged.org.apache.commons.lang3.StringUtils;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static com.grotor.snipetochnya.util.SecurityConstants.BEARER;

@UtilityClass
@Slf4j
public class HttpSettingUtil {
    public String getTokenFromAuthorizationHeader(String authorizationHeader) {
        return Optional.ofNullable(authorizationHeader)
                .filter(StringUtils::isNotBlank)
                .map(bearer -> StringUtils.removeStart(bearer, BEARER).trim())
                .filter(StringUtils::isNotBlank)
                .orElse(null);
    }

    public String getTokenFromValidatedAuthorizationHeader(String authorizationHeader) {

        if (authorizationHeader == null) {
            return null;
        }

        log.info("Loading user for Authorization header: {}", authorizationHeader);

        if (!authorizationHeader.startsWith(BEARER)) {
            throw new AuthenticationHeaderException("Invalid authentication scheme found in Authorization header");
        }

        String token = getTokenFromAuthorizationHeader(authorizationHeader);
        if (token == null) {
            throw new AuthenticationHeaderException("Authorization header token is empty");
        }

        return token;
    }

}
