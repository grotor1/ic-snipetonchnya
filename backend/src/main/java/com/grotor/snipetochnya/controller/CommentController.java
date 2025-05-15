package com.grotor.snipetochnya.controller;

import com.grotor.snipetochnya.dto.request.CommentRequest;
import com.grotor.snipetochnya.dto.response.CommentResponse;
import com.grotor.snipetochnya.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<CommentResponse> getAllComments() {
        return commentService.getAllComments();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public CommentResponse getCommentById(@PathVariable UUID id) {
        return commentService.getCommentById(id);
    }

    @PostMapping
    public CommentResponse addComment(@RequestBody CommentRequest commentRequest) {
        return commentService.createComment(commentRequest);
    }

    @PatchMapping("/{id}")
    public CommentResponse updateComment(@RequestBody CommentRequest commentRequest, @PathVariable UUID id) {
        return commentService.updateComment(commentRequest, id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable UUID id) {
        commentService.deleteComment(id);
    }
}