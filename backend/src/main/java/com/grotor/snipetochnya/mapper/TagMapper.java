package com.grotor.snipetochnya.mapper;

import com.grotor.snipetochnya.dto.response.TagResponse;
import com.grotor.snipetochnya.dto.response.TechResponse;
import com.grotor.snipetochnya.model.Tag;
import com.grotor.snipetochnya.model.Tech;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagResponse toResponse(Tag tag);
}
