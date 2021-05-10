package org.pepo.record.controller;

import org.pepo.record.SwaggerCodgen.api.OrdersApi;
import org.pepo.record.SwaggerCodgen.model.OrderPagedResponseOpenApi;
import org.pepo.record.SwaggerCodgen.model.OrderResponseOpenApi;
import org.pepo.record.mapper.OrdersEntityOpenApiMapper;
import org.pepo.record.service.orders.OrdersService;
import org.pepo.record.service.record.RecordService;
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
    public ResponseEntity<OrderResponseOpenApi> newOrder(final OrderResponseOpenApi orderResponseOpenApi) {
        return ResponseEntity.ok(ordersService.save(ordersEntityOpenApiMapper.orderResponseOpenApiToOrders(orderResponseOpenApi)));
    }

    @Override
    public ResponseEntity<OrderPagedResponseOpenApi> getAllOrders(Integer page, Integer size) {
        return ResponseEntity.ok(ordersService.findAll(page, size));
    }
}
