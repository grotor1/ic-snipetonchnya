package com.grotor.snipetochnya.mapper;

import com.grotor.snipetochnya.model.Role;
import com.grotor.snipetochnya.dto.response.AccountResponse;
import com.grotor.snipetochnya.model.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountResponse toResponse(Account account);

    default com.grotor.snipetochnya.dto.Role map(Role role) {
        return com.grotor.snipetochnya.dto.Role.valueOf(role.getKey());
    }
}

