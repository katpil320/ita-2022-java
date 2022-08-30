package sk.martinliptak.ita.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sk.martinliptak.ita.domain.OrderStatus;
import sk.martinliptak.ita.model.OrderDto;
import sk.martinliptak.ita.service.impl.OrderServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/orders")
public class OrderController {
    private final OrderServiceImpl orderService;

    @PostMapping("cart/{cartId}")
    public OrderDto createOrder(@PathVariable("cartId") Long cartId) {
        return orderService.createOrder(cartId);
    }

    @GetMapping
    public List<OrderDto> findOrders() {
        return orderService.findAllOrders();
    }

    @PutMapping("{orderId}/status/{status}")
    public OrderDto updateOrder(@PathVariable("orderId") Long orderId, @PathVariable("status") OrderStatus status) {
        return orderService.updateStatus(orderId, status);
    }
}
