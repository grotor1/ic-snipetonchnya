package com.grotor.snipetochnya.service;

import com.grotor.snipetochnya.dto.request.CommentRequest;
import com.grotor.snipetochnya.dto.response.CommentResponse;
import com.grotor.snipetochnya.model.Comment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentService {

    List<CommentResponse> getAllComments();

    CommentResponse getCommentById(UUID id);

    CommentResponse createComment(CommentRequest comment);

    CommentResponse updateComment(CommentRequest comment, UUID id);

    void deleteComment(UUID id);
}