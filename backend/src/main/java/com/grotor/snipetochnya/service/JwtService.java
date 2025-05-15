package com.grotor.snipetochnya.service;

import com.grotor.snipetochnya.security.userdetails.UserAccount;

import java.util.Map;

public interface JwtService {
    String extractLogin(String token);

    Boolean validateToken(String token, UserAccount userAccount);

    String generateToken(String username, Map<String, Object> data);
}
