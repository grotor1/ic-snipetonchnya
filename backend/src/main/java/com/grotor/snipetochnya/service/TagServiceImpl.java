package com.grotor.snipetochnya.service;

import com.grotor.snipetochnya.dto.request.TagRequest;
import com.grotor.snipetochnya.dto.response.TagResponse;
import com.grotor.snipetochnya.mapper.TagMapper;
import com.grotor.snipetochnya.model.Tag;
import com.grotor.snipetochnya.repository.TagJpaRepository;
import com.grotor.snipetochnya.security.exception.TagNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagJpaRepository tagJpaRepository;
    private final TagMapper tagMapper;

    @Override
    public List<TagResponse> getAllTags() {
        return tagJpaRepository.findAll().stream().map(tagMapper::toResponse).toList();
    }

    @Override
    public TagResponse getTagById(UUID id) {
        return tagJpaRepository.findById(id).map(tagMapper::toResponse).orElseThrow(() -> new TagNotFoundException(id));
    }

    @Override
    public TagResponse createTag(TagRequest tagRequest) {
        Tag tag = Tag.builder()
                .createdAt(LocalDateTime.now())
                .label(tagRequest.label())
                .build();
        return tagMapper.toResponse(tagJpaRepository.save(tag));
    }

    @Override
    public TagResponse updateTag(TagRequest tagRequest, UUID id) {
        Tag tag = tagJpaRepository.findById(id).orElseThrow(() -> new TagNotFoundException(id));
        tag.setLabel(tagRequest.label() == null ? tag.getLabel() : tagRequest.label());
        return tagMapper.toResponse(tagJpaRepository.save(tag));
    }

    @Override
    public void deleteTag(UUID id) {
        tagJpaRepository.deleteById(id);
    }
}
