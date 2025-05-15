package com.grotor.snipetochnya.mapper;

import com.grotor.snipetochnya.dto.request.TagRequest;
import com.grotor.snipetochnya.dto.request.TechRequest;
import com.grotor.snipetochnya.dto.response.TechResponse;
import com.grotor.snipetochnya.model.Tag;
import com.grotor.snipetochnya.model.Tech;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TechMapper {
    TechResponse toResponse(Tech tech);
}
