package sk.martinliptak.ita.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sk.martinliptak.ita.model.CartDto;
import sk.martinliptak.ita.service.impl.CartServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/carts")
public class CartController {
    private final CartServiceImpl cartService;

    @GetMapping("{id}")
    public CartDto findCart(@PathVariable("id") Long id) {
        return cartService.findCart(id);
    }

    @PostMapping("products/{productId}")
    public CartDto createCart(@PathVariable("productId") Long productId) {
        return cartService.createCart(productId);
    }

    @PutMapping("{id}/products/{productId}")
    public CartDto addToCart(@PathVariable("id") Long id, @PathVariable("productId") Long productId) {
        return cartService.addToCart(id, productId);
    }
}
