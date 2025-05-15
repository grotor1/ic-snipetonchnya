package com.grotor.snipetochnya.service;

import com.grotor.snipetochnya.dto.request.SearchRequest;
import com.grotor.snipetochnya.dto.response.SearchResponse;
import com.grotor.snipetochnya.mapper.SearchMapper;
import com.grotor.snipetochnya.model.Post;
import com.grotor.snipetochnya.model.Search;
import com.grotor.snipetochnya.repository.AccountJpaRepository;
import com.grotor.snipetochnya.repository.SearchJpaRepository;
import com.grotor.snipetochnya.repository.TagJpaRepository;
import com.grotor.snipetochnya.repository.TechJpaRepository;
import com.grotor.snipetochnya.security.exception.*;
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
public class SearchServiceImpl implements SearchService {
    private final SearchJpaRepository searchJpaRepository;
    private final SearchMapper searchMapper;
    private final AccountJpaRepository accountJpaRepository;
    private final BaseUserContextHolder baseUserContextHolder;
    private final TagJpaRepository tagJpaRepository;
    private final TechJpaRepository techJpaRepository;

    @Override
    public List<SearchResponse> getAllSearches() {
        return searchJpaRepository.findAll().stream().filter((item) -> item.getAuthor().getId().equals(baseUserContextHolder.getUserAccountFromSecurityContext().getId())).map(searchMapper::toResponse).toList();
    }

    @Override
    public SearchResponse getSearchById(UUID id) {
        return searchJpaRepository.findById(id).map(searchMapper::toResponse).orElseThrow(() -> new SearchNotFoundException(id));
    }

    @Override
    public SearchResponse createSearch(SearchRequest searchRequest) {
        Search search = Search.builder()
                .name(searchRequest.name())
                .titleEntry(searchRequest.titleEntry())
                .author(
                        accountJpaRepository.findByLogin(
                                baseUserContextHolder.getUserAccountFromSecurityContext().getLogin()
                        ).orElseThrow(
                                () -> new AuthenticationHeaderException("Username not found")
                        )
                )
                .tags(
                        Optional.ofNullable(searchRequest.tags()).map(item -> item.stream().map(
                                (id) -> tagJpaRepository.findById(id).orElseThrow(() -> new TagNotFoundException(id))
                        ).toList()).get()
                )
                .techs(
                        searchRequest.techs()
                                .stream().map(
                                        (id) -> techJpaRepository.findById(id).orElseThrow(() -> new TechNotFoundException(id))
                                ).toList()
                )
                .createdAt(LocalDateTime.now())
                .build();
        return searchMapper.toResponse(searchJpaRepository.save(search));
    }

    @Override
    public SearchResponse updateSearch(SearchRequest searchRequest, UUID id) {
        Search search = searchJpaRepository.findById(id).orElseThrow(() -> new SearchNotFoundException(id));
        search.setTitleEntry(searchRequest.titleEntry() == null ? search.getTitleEntry() : searchRequest.titleEntry());

        search.setTags(searchRequest.tags() == null ?
                search.getTags() :
                searchRequest.tags()
                        .stream().map(
                                (tagId) -> tagJpaRepository.findById(tagId).orElseThrow(() -> new TagNotFoundException(tagId))
                        ).toList()
        );

        search.setTechs(searchRequest.techs() == null ?
                search.getTechs() :
                searchRequest.techs()
                        .stream().map(
                                (techId) -> techJpaRepository.findById(techId).orElseThrow(() -> new TechNotFoundException(techId))
                        ).toList()
        );

        return searchMapper.toResponse(searchJpaRepository.save(search));
    }

    @Override
    public void deleteSearch(UUID id) {
        searchJpaRepository.deleteById(id);
    }
}
