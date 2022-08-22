package sk.martinliptak.ita.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sk.martinliptak.ita.model.OrderDto;
import sk.martinliptak.ita.service.impl.OrderServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/orders")
public class OrderController {
    private final OrderServiceImpl orderService;

    @PostMapping("cart/{cartId}")
    public OrderDto createOrder(@PathVariable("cartId") Long cartId) {
        return orderService.createOrder(cartId);
    }
}
