package sk.martinliptak.ita.mother;

import sk.martinliptak.ita.domain.Cart;
import sk.martinliptak.ita.model.CartDto;

import java.util.HashSet;
import java.util.Set;

import static sk.martinliptak.ita.mother.ProductMother.*;

public class CartMother {
    public static Cart prepareCart() {
        Cart cart = new Cart()
                .setProducts(new HashSet<>());
        cart.getProducts().add(prepareProduct());
        cart.setId(1L);
        return cart;
    }

    public static Cart prepareCart1() {
        Cart cart = new Cart()
                .setProducts(new HashSet<>());
        cart.getProducts().add(prepareProduct());
        cart.getProducts().add(prepareProduct1());

        cart.setId(1L);
        return cart;
    }

    public static CartDto prepareCartDto() {
        return new CartDto()
                .setId(1L)
                .setProducts(Set.of(prepareProductSimpleDto()));
    }

    public static CartDto prepareCartDto1() {
        return new CartDto()
                .setId(1L)
                .setProducts(Set.of(prepareProductSimpleDto(), prepareProductSimpleDto1()));
    }
}
