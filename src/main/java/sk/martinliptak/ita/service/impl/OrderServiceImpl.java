package sk.martinliptak.ita.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.martinliptak.ita.domain.Cart;
import sk.martinliptak.ita.domain.Order;
import sk.martinliptak.ita.domain.OrderStatus;
import sk.martinliptak.ita.exception.CartNotFoundException;
import sk.martinliptak.ita.mapper.OrderMapper;
import sk.martinliptak.ita.model.OrderDto;
import sk.martinliptak.ita.repository.CartRepository;
import sk.martinliptak.ita.repository.OrderRepository;
import sk.martinliptak.ita.service.OrderService;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public OrderDto createOrder(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException(cartId));
        Order order = new Order()
                .setStatus(OrderStatus.NEW)
                .setProducts(cart.getProducts().stream().collect(Collectors.toSet()));
        orderRepository.save(order);
        return orderMapper.toDto(order);
    }
}
