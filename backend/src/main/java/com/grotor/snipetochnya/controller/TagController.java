package com.grotor.snipetochnya.controller;

import com.grotor.snipetochnya.dto.request.TagRequest;
import com.grotor.snipetochnya.dto.response.TagResponse;
import com.grotor.snipetochnya.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @GetMapping
    public List<TagResponse> getAllTags() {
        return tagService.getAllTags();
    }

    @GetMapping("/{id}")
    public TagResponse getTagById(@PathVariable UUID id) {
        return tagService.getTagById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public TagResponse addTag(@RequestBody TagRequest tagRequest) {
        return tagService.createTag(tagRequest);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{id}")
    public TagResponse updateTag(@RequestBody TagRequest tagRequest, @PathVariable UUID id) {
        return tagService.updateTag(tagRequest, id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTag(@PathVariable UUID id) {
        tagService.deleteTag(id);
    }
}
