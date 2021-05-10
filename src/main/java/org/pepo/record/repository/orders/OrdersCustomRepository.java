package org.pepo.record.repository.orders;

import org.pepo.record.entity.Artist;
import org.pepo.record.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrdersCustomRepository {

    Page<Orders> findByUser(Pageable pageable, String user);
}
