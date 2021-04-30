package org.pepo.record.repository.artist;

import org.apache.commons.lang3.StringUtils;
import org.pepo.record.entity.Artist;
import org.pepo.record.entity.Artist_;
import org.pepo.record.repository.commons.PaginationManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ArtistRepositoryImpl implements ArtistCustomRepository {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PaginationManagement paginationManagement;

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

    @Override
    public Page<Artist> findFilterByName(Pageable pageable, String name) {
        CriteriaQuery<Artist> cq = getArtistCriteriaQuery(name);
        return paginationManagement.findAll(cq, pageable);
    }

    /**
     * gets artist critera query according to name
     * @param name to filter
     * @return criteria query {{@link CriteriaQuery}}
     */
    private CriteriaQuery<Artist> getArtistCriteriaQuery(final String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Artist> cq = cb.createQuery(Artist.class);
        Root<Artist> artist = cq.from(Artist.class);
        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.isNotBlank(name)) {
            predicates.add(cb.like(cb.lower(artist.get(Artist_.NAME)), "%" + name.toLowerCase() + "%"));
        }
        cq.where(predicates.toArray(new Predicate[0]));
        return cq;
    }
}
