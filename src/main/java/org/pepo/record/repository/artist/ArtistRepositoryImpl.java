package org.pepo.record.repository.artist;

import org.pepo.record.entity.Artist;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ArtistRepositoryImpl implements ArtistCustomRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Artist> findAll(final int pageNumber, final int pageSize) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        countQuery.select(cb.count(countQuery.from(Artist.class)));
        Long count = entityManager.createQuery(countQuery).getSingleResult();
        CriteriaQuery<Artist> cq = cb.createQuery(Artist.class);
        Root<Artist> from = cq.from(Artist.class);
        CriteriaQuery<Artist> select = cq.select(from);
        TypedQuery<Artist> tq = entityManager.createQuery(select);
        while (pageNumber < count.intValue()) {
            tq.setFirstResult(pageNumber - 1);
            tq.setMaxResults(pageSize);
        }
        return tq.getResultList();
    }
}
