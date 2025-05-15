   package com.grotor.snipetochnya.repository;

import com.grotor.snipetochnya.model.Post;
import com.grotor.snipetochnya.model.Tag;
import com.grotor.snipetochnya.model.Tech;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface PostJpaRepository extends JpaRepository<Post, UUID>, PostSearchRepository {
    @Query(value = "select p from Post p where" +
            "(cast(:authorId as string) is null or p.author.id = :authorId) and " +
            "(cast(:tags as string) is null or :tags member of p.tags) and " +
            "(cast(:techs as string) is null or :techs member of p.techs) and " +
            "(cast(:titleEntry as string) is null or lower(p.title) like lower(concat('%', :titleEntry, '%')))" +
            "order by p.createdAt desc")
    List<Post> findByFilter(@Param("authorId") UUID authorId, @Param("tags") List<Tag> tags, @Param("techs") List<Tech> techs, @Param("titleEntry") String titleEntry);
}