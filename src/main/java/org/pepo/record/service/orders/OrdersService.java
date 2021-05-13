package org.pepo.record.service.orders;

import org.pepo.record.SwaggerCodgen.model.OrderPagedResponseOpenApi;
import org.pepo.record.SwaggerCodgen.model.OrderResponseOpenApi;
import org.pepo.record.entity.Orders;

public interface OrdersService {

    OrderResponseOpenApi save(Orders orders);

    OrderPagedResponseOpenApi findAll(Integer page, Integer size, String user);

    OrderResponseOpenApi findById(Integer orderId);
}