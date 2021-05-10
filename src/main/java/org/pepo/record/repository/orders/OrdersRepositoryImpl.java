package org.pepo.record.repository.orders;

import org.apache.commons.lang3.StringUtils;
import org.pepo.record.entity.Orders;
import org.pepo.record.entity.Orders_;
import org.pepo.record.repository.commons.PaginationManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class OrdersRepositoryImpl implements OrdersCustomRepository {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PaginationManagement paginationManagement;

    @Override
    public Page<Orders> findByUser(final Pageable pageable, final String user) {
        CriteriaQuery<Orders> cq = getOrderCriteriaQuery(user);
        return paginationManagement.findAll(cq, pageable);
    }

    /**
     * gets orders critera query according to user
     * @param user to filter
     * @return criteria query {{@link CriteriaQuery}}
     */
    private CriteriaQuery<Orders> getOrderCriteriaQuery(final String user) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Orders> cq = cb.createQuery(Orders.class);
        Root<Orders> orders = cq.from(Orders.class);
        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.isNotBlank(user)) {
            predicates.add(cb.equal(orders.get(Orders_.USER), user));
        }
        cq.where(predicates.toArray(new Predicate[0]));
        return cq;
    }
}
