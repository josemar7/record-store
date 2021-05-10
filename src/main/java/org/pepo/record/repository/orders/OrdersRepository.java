package org.pepo.record.repository.orders;

import org.pepo.record.entity.Orders;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrdersRepository extends PagingAndSortingRepository<Orders, Integer>, OrdersCustomRepository {
}
