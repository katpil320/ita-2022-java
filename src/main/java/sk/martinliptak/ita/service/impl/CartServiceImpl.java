package sk.martinliptak.ita.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.martinliptak.ita.domain.Cart;
import sk.martinliptak.ita.domain.Product;
import sk.martinliptak.ita.exception.CartNotFoundException;
import sk.martinliptak.ita.exception.ProductNotFoundException;
import sk.martinliptak.ita.mapper.CartMapper;
import sk.martinliptak.ita.model.CartDto;
import sk.martinliptak.ita.repository.CartRepository;
import sk.martinliptak.ita.repository.ProductRepository;
import sk.martinliptak.ita.service.CartService;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;

    @Override
    @Transactional(readOnly = true)
    public CartDto findCart(Long id) {
        return cartRepository.findById(id)
                .map(cartMapper::toDto)
                .orElseThrow(() -> new CartNotFoundException(id));
    }

    @Override
    @Transactional
    public CartDto createCart(Long productId) {
        Cart cart = new Cart().setProducts(
                Set.of(
                productRepository.findById(productId)
                        .orElseThrow(() -> new ProductNotFoundException(productId))
                )
        );
        cartRepository.save(cart);
        return cartMapper.toDto(cart);
    }

    @Override
    @Transactional
    public CartDto addToCart(Long id, Long productId) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new CartNotFoundException(id));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        cart.getProducts().add(product);
        return cartMapper.toDto(cart);
    }
}
