package com.grotor.snipetochnya.repository;

import com.grotor.snipetochnya.model.Post;
import com.grotor.snipetochnya.model.Search;
import liquibase.pro.packaged.R;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class PostSearchRepositoryImpl implements PostSearchRepository {
    private final EntityManager em;

    @Override
    public List<Post> findPostsBySearchId(UUID searchId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Search> cr = cb.createQuery(Search.class);
        Root<Search> r = cr.from(Search.class);

        cr.select(r).where(cb.equal(r.get("id"), searchId));
        Search search = em.createQuery(cr).getSingleResult();

        CriteriaQuery<Post> crp = cb.createQuery(Post.class);
        Root<Post> rp = crp.from(Post.class);
        crp.select(rp).where(cb.and(
                cb.like(cb.lower(rp.get("title")), "%" + search.getTitleEntry() + "%")
        ));
        return em.createQuery(crp).getResultList();
    }
}
