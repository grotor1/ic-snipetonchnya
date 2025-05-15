package com.grotor.snipetochnya.service;


import com.grotor.snipetochnya.dto.request.AccountRequest;
import com.grotor.snipetochnya.dto.request.AuthenticationRequest;
import com.grotor.snipetochnya.dto.request.TokenRefreshRequest;
import com.grotor.snipetochnya.dto.response.AccountResponse;
import com.grotor.snipetochnya.dto.response.TokenCoupleResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountService {
    void registration(AccountRequest accountRequest);

    TokenCoupleResponse login(AuthenticationRequest authenticationRequest);

    TokenCoupleResponse refresh(TokenRefreshRequest tokenRefreshRequest);

    AccountResponse getUserInfoByToken(String token);

    AccountResponse getCurrentUser();

    List<AccountResponse> getAll();

    AccountResponse getById(UUID id);

    AccountResponse toggleBlock(UUID id);

    AccountResponse update(AccountRequest accountRequest, UUID id);

    void delete(UUID id);
}
