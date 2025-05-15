package com.grotor.snipetochnya.service;

import com.grotor.snipetochnya.dto.request.PostRequest;
import com.grotor.snipetochnya.dto.response.Pagination;
import com.grotor.snipetochnya.dto.response.PostResponse;
import com.grotor.snipetochnya.model.Post;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostService {

    List<PostResponse> getAllPosts(
            UUID author,
            List<UUID> tags,
            List<UUID> techs,
            String titleEntry
    );

    List<PostResponse> getPostsBySearch(
            UUID search
    );

    PostResponse getPostById(UUID id);

    PostResponse createPost(PostRequest post);

    PostResponse updatePost(PostRequest postRequest, UUID id);

    void deletePost(UUID id);
}