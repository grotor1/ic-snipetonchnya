package com.grotor.snipetochnya.controller;

import com.grotor.snipetochnya.dto.request.PostRequest;
import com.grotor.snipetochnya.dto.response.Pagination;
import com.grotor.snipetochnya.dto.response.PostResponse;
import com.grotor.snipetochnya.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PreAuthorize("permitAll()")
    @GetMapping
    public List<PostResponse> getAllPosts(
            @RequestParam(required = false) UUID author,
            @RequestParam(required = false) List<UUID> tags,
            @RequestParam(required = false) List<UUID> techs,
            @RequestParam(required = false) String titleEntry
            ) {
        return postService.getAllPosts(author, tags, techs, titleEntry);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public PostResponse getPostById(@PathVariable UUID id) {
        return postService.getPostById(id);
    }

    @PostMapping
    public PostResponse addPost(@RequestBody PostRequest postRequest) {
        return postService.createPost(postRequest);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{id}")
    public PostResponse updatePost(@RequestBody PostRequest postRequest, @PathVariable UUID id) {
        return postService.updatePost(postRequest, id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable UUID id) {
        postService.deletePost(id);
    }
}