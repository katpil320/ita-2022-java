package sk.martinliptak.ita.service;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import sk.martinliptak.ita.domain.Cart;
import sk.martinliptak.ita.domain.Product;
import sk.martinliptak.ita.exception.CartNotFoundException;
import sk.martinliptak.ita.mapper.CartMapper;
import sk.martinliptak.ita.model.CartDto;
import sk.martinliptak.ita.repository.CartRepository;
import sk.martinliptak.ita.repository.ProductRepository;
import sk.martinliptak.ita.service.impl.CartServiceImpl;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static sk.martinliptak.ita.mother.CartMother.*;
import static sk.martinliptak.ita.mother.ProductMother.prepareProduct;
import static sk.martinliptak.ita.mother.ProductMother.prepareProduct1;

@ExtendWith(MockitoExtension.class)
class CartServiceTest implements WithAssertions {
    @InjectMocks
    private CartServiceImpl cartService;
    @Mock
    private CartRepository cartRepository;
    @Mock
    private CartMapper cartMapper;
    @Mock
    private ProductRepository productRepository;
    @Captor
    ArgumentCaptor<Cart> cartCaptor;

    @Test
    void findCart() {
        Long targetCartId = 1L;
        Cart cart = prepareCart();
        CartDto expectedResult = prepareCartDto();

        when(cartRepository.findById(targetCartId)).thenReturn(Optional.of(cart));
        when(cartMapper.toDto(cart)).thenReturn(expectedResult);

        CartDto result = cartService.findCart(targetCartId);

        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);

        verify(cartRepository).findById(targetCartId);
        verify(cartMapper).toDto(cart);

    }

    @Test
    void createCart() {
        Long productId = 1L;
        Product product = prepareProduct();
        Cart cart = prepareCart();
        CartDto expectedResult = prepareCartDto();

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(cartMapper.toDto(any(Cart.class))).thenReturn(expectedResult);
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        CartDto result = cartService.createCart(productId);

        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
        verify(cartRepository).save(any(Cart.class));
        verify(productRepository).findById(productId);
    }

    @Test
    void addToCart() {
        Long cartId = 1L;
        Long productId = 2L;
        Product targetProduct = prepareProduct1();
        Cart targetCart = prepareCart();
        Set<Product> expectedProductsInCart = prepareCart1().getProducts();
        CartDto expectedResult = prepareCartDto1();

        when(cartRepository.findById(cartId)).thenReturn(Optional.of(targetCart));
        when(productRepository.findById(productId)).thenReturn(Optional.of(targetProduct));
        when(cartMapper.toDto(targetCart)).thenReturn(expectedResult);

        CartDto result = cartService.addToCart(cartId, productId);

        verify(cartMapper).toDto(cartCaptor.capture()); // Capture modified cart
        assertThat(cartCaptor.getValue().getProducts()).usingRecursiveComparison().isEqualTo(expectedProductsInCart);
        assertThat(result.getProducts()).usingRecursiveComparison().isEqualTo(expectedResult.getProducts());
    }

    @Test
    void fetchingNonExistingCart() throws CartNotFoundException {
        Long id = 1L;

        when(cartRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(CartNotFoundException.class, () -> cartService.findCart(id));
        verify(cartRepository).findById(id);
    }

    @Test
    void addingToNonExistingCart() throws CartNotFoundException {
        Long id = 1L;
        Long productId = 1L;

        when(cartRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(CartNotFoundException.class, () -> cartService.addToCart(id, productId));
        verify(cartRepository).findById(id);
    }
}