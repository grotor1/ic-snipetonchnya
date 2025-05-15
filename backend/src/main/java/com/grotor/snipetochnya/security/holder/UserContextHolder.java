package com.grotor.snipetochnya.security.holder;

import com.grotor.snipetochnya.dto.response.AccountResponse;
import com.grotor.snipetochnya.security.userdetails.UserAccount;

public interface UserContextHolder {
    UserAccount getUserAccountFromSecurityContext();
}
