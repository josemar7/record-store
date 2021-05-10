package org.pepo.record.service.orders;

import lombok.AllArgsConstructor;
import org.pepo.record.SwaggerCodgen.model.OrderPagedResponseOpenApi;
import org.pepo.record.SwaggerCodgen.model.OrderResponseOpenApi;
import org.pepo.record.entity.Orders;
import org.pepo.record.mapper.OrdersEntityOpenApiMapper;
import org.pepo.record.repository.orders.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private final OrdersRepository ordersRepository;

    @Autowired
    private final OrdersEntityOpenApiMapper ordersEntityOpenApiMapper;

    @Override
    public OrderResponseOpenApi save(final Orders orders) {
        orders.setDate(LocalDateTime.now());
        orders.getOrdersLines().get(0).setOrders(orders);
        return ordersEntityOpenApiMapper.ordersToOrderResponseOpenApi(ordersRepository.save(orders));
    }

    @Override
    public OrderPagedResponseOpenApi findAll(final Integer page, final Integer size, final String user) {
        List<OrderResponseOpenApi> list = new ArrayList<>();
        OrderPagedResponseOpenApi responseOpenApi = new OrderPagedResponseOpenApi();
        Pageable paging = PageRequest.of(page, size);
        Page<Orders> orderPage = ordersRepository.findByUser(paging, user);
        orderPage.getContent().forEach(order -> list.add(ordersEntityOpenApiMapper.ordersToOrderResponseOpenApi(order)));
        responseOpenApi.setTotalElements(orderPage.getTotalElements());
        responseOpenApi.setTotalPages(orderPage.getTotalPages());
        responseOpenApi.setResult(list);
        return responseOpenApi;
    }
}