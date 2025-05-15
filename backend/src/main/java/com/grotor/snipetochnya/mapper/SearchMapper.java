package com.grotor.snipetochnya.mapper;

import com.grotor.snipetochnya.dto.response.PostResponse;
import com.grotor.snipetochnya.dto.response.SearchResponse;
import com.grotor.snipetochnya.model.Post;
import com.grotor.snipetochnya.model.Search;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SearchMapper {
    SearchResponse toResponse(Search search);
}
