package org.pepo.record.mapper;

import org.mapstruct.Mapper;
import org.pepo.record.SwaggerCodgen.model.OrderResponseOpenApi;
import org.pepo.record.entity.Orders;

@Mapper(componentModel = "spring")
public interface OrdersEntityOpenApiMapper {

    OrderResponseOpenApi ordersToOrderResponseOpenApi(Orders orders);

    Orders orderResponseOpenApiToOrders(OrderResponseOpenApi orderResponseOpenApi);
}
