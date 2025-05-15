package com.grotor.snipetochnya.service;

import com.grotor.snipetochnya.dto.request.TagRequest;
import com.grotor.snipetochnya.dto.response.TagResponse;
import com.grotor.snipetochnya.model.Tag;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TagService {

    List<TagResponse> getAllTags();

    TagResponse getTagById(UUID id);

    TagResponse createTag(TagRequest tag);

    TagResponse updateTag(TagRequest tag, UUID id);

    void deleteTag(UUID id);
}