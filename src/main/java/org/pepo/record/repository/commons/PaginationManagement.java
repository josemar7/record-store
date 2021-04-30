package org.pepo.record.repository.commons;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.criteria.CriteriaQuery;

public interface PaginationManagement {

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
    <T> Page<T> findAll(CriteriaQuery<T> cq, Pageable pageable);
}
