package com.grotor.snipetochnya.mapper;

import com.grotor.snipetochnya.dto.response.CommentResponse;
import com.grotor.snipetochnya.dto.response.TechResponse;
import com.grotor.snipetochnya.model.Comment;
import com.grotor.snipetochnya.model.Role;
import com.grotor.snipetochnya.model.Tech;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentResponse toResponse(Comment comment);

    default com.grotor.snipetochnya.dto.Role map(Role role) {
        return com.grotor.snipetochnya.dto.Role.valueOf(role.getKey());
    }
}
