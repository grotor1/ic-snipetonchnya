package com.grotor.snipetochnya.service;

import com.grotor.snipetochnya.dto.request.CommentRequest;
import com.grotor.snipetochnya.dto.response.CommentResponse;
import com.grotor.snipetochnya.mapper.CommentMapper;
import com.grotor.snipetochnya.model.Comment;
import com.grotor.snipetochnya.repository.AccountJpaRepository;
import com.grotor.snipetochnya.repository.CommentJpaRepository;
import com.grotor.snipetochnya.repository.PostJpaRepository;
import com.grotor.snipetochnya.security.exception.AuthenticationHeaderException;
import com.grotor.snipetochnya.security.exception.CommentNotFoundException;
import com.grotor.snipetochnya.security.exception.NotFoundException;
import com.grotor.snipetochnya.security.exception.PostNotFoundException;
import com.grotor.snipetochnya.security.holder.BaseUserContextHolder;
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
public class CommentServiceImpl implements CommentService{
    private final CommentJpaRepository commentJpaRepository;
    private final CommentMapper commentMapper;
    private final PostJpaRepository postJpaRepository;
    private final AccountJpaRepository accountJpaRepository;
    private final BaseUserContextHolder baseUserContextHolder;

    @Override
    public List<CommentResponse> getAllComments() {
        return commentJpaRepository.findAll().stream().map(commentMapper::toResponse).toList();
    }

    @Override
    public CommentResponse getCommentById(UUID id) {
        return commentJpaRepository.findById(id).map(commentMapper::toResponse).orElseThrow(() -> new CommentNotFoundException(id));
    }

    @Override
    public CommentResponse createComment(CommentRequest commentRequest) {
        Comment comment = Comment.builder()
                .post(postJpaRepository.findById(commentRequest.postId()).orElseThrow(() -> new PostNotFoundException(commentRequest.postId())))
                .content(commentRequest.content())
                .createdAt(LocalDateTime.now())
                .author(
                        accountJpaRepository.findByLogin(
                                baseUserContextHolder.getUserAccountFromSecurityContext().getLogin()
                        ).orElseThrow(
                                () -> new AuthenticationHeaderException("User not found")
                        )
                )
                .build();

        return commentMapper.toResponse(commentJpaRepository.save(comment));
    }

    @Override
    public CommentResponse updateComment(CommentRequest commentRequest, UUID id) {
        Comment comment = commentJpaRepository.findById(id).orElseThrow(() -> new CommentNotFoundException(id));
        comment.setContent(commentRequest.content() == null ? comment.getContent() : commentRequest.content());
        return commentMapper.toResponse(commentJpaRepository.save(comment));
    }

    @Override
    public void deleteComment(UUID id) {
        commentJpaRepository.deleteById(id);
    }
}
