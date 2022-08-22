package sk.martinliptak.ita.service;

import sk.martinliptak.ita.model.OrderDto;

public interface OrderService {
    OrderDto createOrder(Long cartId);
}
