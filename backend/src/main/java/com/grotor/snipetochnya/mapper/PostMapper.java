package com.grotor.snipetochnya.mapper;

import com.grotor.snipetochnya.dto.response.PostResponse;
import com.grotor.snipetochnya.dto.response.TechResponse;
import com.grotor.snipetochnya.model.Post;
import com.grotor.snipetochnya.model.Role;
import com.grotor.snipetochnya.model.Tech;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostResponse toResponse(Post post);

    default com.grotor.snipetochnya.dto.Role map(Role role) {
        return com.grotor.snipetochnya.dto.Role.valueOf(role.getKey());
    }
}
