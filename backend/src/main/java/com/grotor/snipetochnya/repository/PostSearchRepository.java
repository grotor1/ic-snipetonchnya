package com.grotor.snipetochnya.repository;

import com.grotor.snipetochnya.model.Post;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface PostSearchRepository {
    List<Post> findPostsBySearchId(UUID search);
}
