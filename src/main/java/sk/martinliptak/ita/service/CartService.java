package sk.martinliptak.ita.service;

import sk.martinliptak.ita.model.CartDto;

public interface CartService {
    CartDto findCart(Long id);
    CartDto createCart(Long productId);
    CartDto addToCart(Long id,Long productId);
}
