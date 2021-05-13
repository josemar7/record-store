package org.pepo.record.controller;

import org.pepo.record.SwaggerCodgen.api.OrdersApi;
import org.pepo.record.SwaggerCodgen.model.OrderPagedResponseOpenApi;
import org.pepo.record.SwaggerCodgen.model.OrderResponseOpenApi;
import org.pepo.record.mapper.OrdersEntityOpenApiMapper;
import org.pepo.record.service.orders.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdersController implements OrdersApi {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private OrdersEntityOpenApiMapper ordersEntityOpenApiMapper;

    @Override
    public ResponseEntity<OrderResponseOpenApi> newOrder(final String user, final OrderResponseOpenApi orderResponseOpenApi) {
        return ResponseEntity.ok(ordersService.save(ordersEntityOpenApiMapper.orderResponseOpenApiToOrders(orderResponseOpenApi)));
    }

    @Override
    public ResponseEntity<OrderPagedResponseOpenApi> getAllOrders(final String user, final Integer page, final Integer size) {
        return ResponseEntity.ok(ordersService.findAll(page, size, user));
    }

    @Override
    public ResponseEntity<OrderResponseOpenApi> getOrderById(final Integer orderId) {
        return ResponseEntity.ok(ordersService.findById(orderId));
    }
}
