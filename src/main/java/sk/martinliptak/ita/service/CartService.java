package sk.martinliptak.ita.service;

import sk.martinliptak.ita.model.CartDto;

/**
 * A service for manipulating carts
 * @see sk.martinliptak.ita.domain.Cart
 *
 */
public interface CartService {
    /**
     * Retrieves specific cart
     * @param id of cart
     * @return {@link CartDto}
     * @throws sk.martinliptak.ita.exception.CartNotFoundException if the cart doesn't not exist
     */
    CartDto findCart(Long id);

    /**
     * Creates new cart for given product
     * @param productId
     * @return {@link CartDto}
     * @throws sk.martinliptak.ita.exception.ProductNotFoundException if the product doesn't exist
     */
    CartDto createCart(Long productId);

    /**
     * Adds product to cart
     * @param id of cart
     * @param productId
     * @return {@link  CartDto}
     * @throws sk.martinliptak.ita.exception.CartNotFoundException if the cart doesn't exist
     */
    CartDto addToCart(Long id,Long productId);
}
