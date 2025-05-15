package com.grotor.snipetochnya.service;

import com.grotor.snipetochnya.dto.request.SearchRequest;
import com.grotor.snipetochnya.dto.response.SearchResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SearchService {
    List<SearchResponse> getAllSearches();

    SearchResponse getSearchById(UUID id);

    SearchResponse createSearch(SearchRequest Search);

    SearchResponse updateSearch(SearchRequest Search, UUID id);

    void deleteSearch(UUID id);
}