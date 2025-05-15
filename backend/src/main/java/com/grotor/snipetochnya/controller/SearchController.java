package com.grotor.snipetochnya.controller;

import com.grotor.snipetochnya.dto.request.SearchRequest;
import com.grotor.snipetochnya.dto.response.Pagination;
import com.grotor.snipetochnya.dto.response.PostResponse;
import com.grotor.snipetochnya.dto.response.SearchResponse;
import com.grotor.snipetochnya.service.PostService;
import com.grotor.snipetochnya.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/searches")
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;
    private final PostService postService;

    @GetMapping
    public List<SearchResponse> getAllSearches() {
        return searchService.getAllSearches();
    }

    @GetMapping("/{id}")
    public SearchResponse getSearchById(@PathVariable UUID id) {
        return searchService.getSearchById(id);
    }

    @GetMapping("/{id}/extract")
    public List<PostResponse> getPostsBySearch(
            @PathVariable UUID id
    ) {
        return postService.getPostsBySearch(id);
    }

    @PostMapping
    public SearchResponse addSearch(@RequestBody SearchRequest searchRequest) {
        return searchService.createSearch(searchRequest);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{id}")
    public SearchResponse updateSearch(@RequestBody SearchRequest searchRequest, @PathVariable UUID id) {
        return searchService.updateSearch(searchRequest, id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSearch(@PathVariable UUID id) {
        searchService.deleteSearch(id);
    }
}