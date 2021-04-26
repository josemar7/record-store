package org.pepo.record.repository.record;

import org.apache.commons.lang3.StringUtils;
import org.pepo.record.entity.Artist_;
import org.pepo.record.entity.Format_;
import org.pepo.record.entity.Record;
import org.pepo.record.entity.Record_;
import org.pepo.record.entity.Style_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecordRepositoryImpl implements RecordCustomRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Record> findFilteredRecords(final String name, final String artist, final String format, final String style) {
        CriteriaQuery<Record> cq = getRecordCriteriaQuery(name, artist, format, style);
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public Page<Record> findFilteredRecords(String name, String artist, String format, String style, Pageable pageable) {
        CriteriaQuery<Record> cq = getRecordCriteriaQuery(name, artist, format, style);
        return findAll(cq, pageable);
    }

    /**
     * gets criteria query according parameters
     * @param name record
     * @param artist
     * @param format
     * @param style
     * @return criteria {{@link CriteriaQuery}}
     * @see CriteriaQuery
     */
    private CriteriaQuery<Record> getRecordCriteriaQuery(final String name, final String artist, final String format, final String style) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Record> cq = cb.createQuery(Record.class);
        Root<Record> record = cq.from(Record.class);
        Join<Object, Object> artistJoin = record.join(Record_.ARTIST);
        Join<Object, Object> formatJoin = record.join(Record_.FORMAT);
        Join<Object, Object> styleJoin = artistJoin.join(Artist_.STYLE);
        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.isNotBlank(name)) {
            predicates.add(cb.like(cb.lower(record.get(Record_.NAME)), "%" + name.toLowerCase() + "%"));
        }
        if (StringUtils.isNotBlank(artist)) {
            predicates.add(cb.like(cb.lower(artistJoin.get(Artist_.NAME)), "%" + artist.toLowerCase() + "%"));
        }
        if (StringUtils.isNotBlank(format)) {
            predicates.add(cb.equal(cb.lower(formatJoin.get(Format_.NAME)), format.toLowerCase()));
        }
        if (StringUtils.isNotBlank(style)) {
            predicates.add(cb.equal(cb.lower(styleJoin.get(Style_.NAME)), style.toLowerCase()));
        }
        cq.where(predicates.toArray(new Predicate[0]));
        return cq;
    }

    /**
     * gets paged results according to critera and pagination
     * @param cq criteria
     * @param pageable pagination
     * @param <T> generic class
     * @return page result
     * @see Page
     * @see CriteriaQuery
     * @see Pageable
     */
    private  <T> Page<T> findAll(final CriteriaQuery<T> cq, final Pageable pageable) {
        Class<T> domainClass = cq.getResultType();
        Root<T> root;
        if (CollectionUtils.isEmpty(cq.getRoots())) {
            root = cq.from(domainClass);
        } else {
            root = (Root<T>) cq.getRoots().iterator().next();
        }
        if (pageable == null) {
            List<T> list = findAll(cq);
            return new PageImpl<>(list);
        } else {
            Sort sort = pageable.getSort();
            cq.orderBy(QueryUtils.toOrders(sort, root, entityManager.getCriteriaBuilder()));
            TypedQuery<T> query = entityManager.createQuery(cq);
            Long offset = pageable.getOffset();
            query.setFirstResult(offset.intValue());
            query.setMaxResults(pageable.getPageSize());
            Long total = count(cq);
            List<T> content = total > pageable.getOffset() ? query.getResultList() : Collections.<T> emptyList();
            return new PageImpl<T>(content, pageable, total);
        }
    }

    /**
     * gets results according to critera
     * @param cq critera
     * @param <T> generic class
     * @return result
     * @see CriteriaQuery
     */
    private <T> List<T> findAll(final CriteriaQuery<T> cq) {
        Class<T> domainClass = cq.getResultType();
        if (CollectionUtils.isEmpty(cq.getRoots())) {
            cq.from(domainClass);
        }
        return entityManager.createQuery(cq).getResultList();
    }

    /**
     * result count according criteria
     * @param cq criteria
     * @param <T> generic class
     * @return registers count
     */
    public <T> Long count(final CriteriaQuery<T> cq) {
        return executeCountQuery(getCountQuery(cq));
    }

    /**
     * gets count query
     * @param cq criteria
     * @param <T> generic class
     * @return query {@link TypedQuery}
     * @see CriteriaQuery
     * @see TypedQuery
     */
    public <T> TypedQuery<Long> getCountQuery(final CriteriaQuery<T> cq) {
        Class<T> domainClass = cq.getResultType();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countCq = cb.createQuery(Long.class);
        Root<T> root;
        if (cq.getRestriction() != null) {
            countCq.where(cq.getRestriction());
        }
        if (cq.getGroupRestriction() != null) {
            countCq.having(cq.getGroupRestriction());
        }
        if (cq.getRoots().isEmpty()) {
            root = countCq.from(domainClass);
        } else {
            countCq.getRoots().addAll(cq.getRoots());
            root = (Root<T>) countCq.getRoots().iterator().next();
        }
        countCq.groupBy(cq.getGroupList());
        if (cq.isDistinct()) {
            countCq.select(cb.countDistinct(root));
        } else {
            countCq.select(cb.count(root));
        }
        return entityManager.createQuery(countCq);
    }

    /**
     * executing count query
     * @param query
     * @return rcords number
     * @see TypedQuery
     */
    public Long executeCountQuery(final TypedQuery<Long> query) {
        Assert.notNull(query, "query can not be null.");
        List<Long> totals = query.getResultList();
        Long total = 0L;
        for (Long element : totals) {
            total += element == null ? 0 : element;
        }
        return total;
    }
}
