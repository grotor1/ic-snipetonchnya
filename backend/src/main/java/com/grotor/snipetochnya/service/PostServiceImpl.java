package com.grotor.snipetochnya.service;

import com.grotor.snipetochnya.dto.request.PostRequest;
import com.grotor.snipetochnya.dto.response.Pagination;
import com.grotor.snipetochnya.dto.response.PostResponse;
import com.grotor.snipetochnya.mapper.PostMapper;
import com.grotor.snipetochnya.model.Post;
import com.grotor.snipetochnya.model.Tag;
import com.grotor.snipetochnya.model.Tech;
import com.grotor.snipetochnya.repository.AccountJpaRepository;
import com.grotor.snipetochnya.repository.PostJpaRepository;
import com.grotor.snipetochnya.repository.TagJpaRepository;
import com.grotor.snipetochnya.repository.TechJpaRepository;
import com.grotor.snipetochnya.security.exception.*;
import com.grotor.snipetochnya.security.holder.BaseUserContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {
    private final PostJpaRepository postJpaRepository;
    private final PostMapper postMapper;
    private final AccountJpaRepository accountJpaRepository;
    private final BaseUserContextHolder baseUserContextHolder;
    private final TagJpaRepository tagJpaRepository;
    private final TechJpaRepository techJpaRepository;

    @Override
    public List<PostResponse> getAllPosts(UUID author, List<UUID> tags, List<UUID> techs, String titleEntry) {
        List<Tag> tagsEntity = Optional.ofNullable(tags).map(notNullTags -> notNullTags.stream().map(
                (id) -> tagJpaRepository.findById(id).orElseThrow(() -> new TagNotFoundException(id))
        ).toList()).orElse(null);
        List<Tech> techsEntity = Optional.ofNullable(techs).map(notNullTechs -> notNullTechs.stream().map(
                (id) -> techJpaRepository.findById(id).orElseThrow(() -> new TechNotFoundException(id))
        ).toList()).orElse(null);
        return postJpaRepository.findByFilter(author, tagsEntity, techsEntity, titleEntry).stream().map(postMapper::toResponse).toList();

    }

    @Override
    public List<PostResponse> getPostsBySearch(UUID search) {
        return postJpaRepository.findPostsBySearchId(search).stream().map(postMapper::toResponse).toList();
    }

    @Override
    public PostResponse getPostById(UUID id) {
        return postJpaRepository.findById(id).map(postMapper::toResponse).orElseThrow(() -> new PostNotFoundException(id));
    }

    @Override
    public PostResponse createPost(PostRequest postRequest) {
        Post post = Post.builder()
                .author(
                        accountJpaRepository.findByLogin(
                                baseUserContextHolder.getUserAccountFromSecurityContext().getLogin()
                        ).orElseThrow(
                                () -> new AuthenticationHeaderException("Username not found")
                        )
                )
                .title(postRequest.title())
                .content(postRequest.content())
                .language(postRequest.language())
                .tags(
                        postRequest.tags()
                                .stream().map(
                                        (id) -> tagJpaRepository.findById(id).orElseThrow(() -> new TagNotFoundException(id))
                                ).toList()
                )
                .techs(
                        postRequest.techs()
                                .stream().map(
                                        (id) -> techJpaRepository.findById(id).orElseThrow(() -> new TechNotFoundException(id))
                                ).toList()
                )
                .createdAt(LocalDateTime.now())
                .build();

        log.info(post.toString());

        return postMapper.toResponse(postJpaRepository.save(post));
    }

    @Override
    public PostResponse updatePost(PostRequest postRequest, UUID id) {
        Post post = postJpaRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        post.setTitle(postRequest.title() == null ? post.getTitle() : postRequest.title());
        post.setContent(postRequest.content() == null ? post.getContent() : postRequest.content());

        post.setTags(postRequest.tags() == null ?
                post.getTags() :
                postRequest.tags()
                        .stream().map(
                                (tagId) -> tagJpaRepository.findById(tagId).orElseThrow(() -> new TagNotFoundException(tagId))
                        ).toList()
        );

        post.setLanguage(postRequest.language() == null ? post.getLanguage() : postRequest.language());

        post.setTechs(postRequest.techs() == null ?
                post.getTechs() :
                postRequest.techs()
                        .stream().map(
                                (techId) -> techJpaRepository.findById(techId).orElseThrow(() -> new TechNotFoundException(techId))
                        ).toList()
        );

        return postMapper.toResponse(postJpaRepository.save(post));
    }

    @Override
    public void deletePost(UUID id) {
        postJpaRepository.deleteById(id);
    }
}
