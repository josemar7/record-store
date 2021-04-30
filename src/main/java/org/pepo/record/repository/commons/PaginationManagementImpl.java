package org.pepo.record.repository.commons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;

@Component
public class PaginationManagementImpl implements PaginationManagement {

    @Autowired
    private EntityManager entityManager;

    @Override
    public <T> Page<T> findAll(CriteriaQuery<T> cq, Pageable pageable) {
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
    private  <T> Long count(final CriteriaQuery<T> cq) {
        return executeCountQuery(getCountQuery(cq));
    }

    /**
     * executing count query
     * @param query
     * @return rcords number
     * @see TypedQuery
     */
    private Long executeCountQuery(final TypedQuery<Long> query) {
        Assert.notNull(query, "query can not be null.");
        List<Long> totals = query.getResultList();
        Long total = 0L;
        for (Long element : totals) {
            total += element == null ? 0 : element;
        }
        return total;
    }

    /**
     * gets count query
     * @param cq criteria
     * @param <T> generic class
     * @return query {@link TypedQuery}
     * @see CriteriaQuery
     * @see TypedQuery
     */
    private  <T> TypedQuery<Long> getCountQuery(final CriteriaQuery<T> cq) {
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
}
