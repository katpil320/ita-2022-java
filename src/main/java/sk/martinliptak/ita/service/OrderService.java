package sk.martinliptak.ita.service;

import sk.martinliptak.ita.domain.OrderStatus;
import sk.martinliptak.ita.model.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(Long cartId);
    List<OrderDto> findAllOrders();

    OrderDto updateStatus(Long orderId, OrderStatus status);
}
