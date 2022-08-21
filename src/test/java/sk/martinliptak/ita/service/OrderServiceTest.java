package sk.martinliptak.ita.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sk.martinliptak.ita.domain.Cart;
import sk.martinliptak.ita.domain.Order;
import sk.martinliptak.ita.domain.Product;
import sk.martinliptak.ita.mapper.OrderMapper;
import sk.martinliptak.ita.model.OrderDto;
import sk.martinliptak.ita.repository.CartRepository;
import sk.martinliptak.ita.repository.OrderRepository;
import sk.martinliptak.ita.service.impl.OrderServiceImpl;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static sk.martinliptak.ita.mother.CartMother.prepareCart1;
import static sk.martinliptak.ita.mother.OrderMother.prepareOrder;
import static sk.martinliptak.ita.mother.OrderMother.prepareOrderDto;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Captor
    ArgumentCaptor<Order> orderCaptor;
    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private CartRepository cartRepository;
    @Mock
    private OrderMapper orderMapper;

    @Test
    void createOrder() {
        Cart cart = prepareCart1();
        Long cartId = 1L;
        OrderDto expectedResult = prepareOrderDto();
        Set<Product> expectedProductsInOrder = prepareOrder().getProducts();

        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));
        when(orderMapper.toDto(any(Order.class))).thenReturn(expectedResult);

        OrderDto result = orderService.createOrder(cartId);

        verify(orderMapper).toDto(orderCaptor.capture()); // Capture new order

        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
        assertThat(orderCaptor.getValue().getProducts()).usingRecursiveComparison().isEqualTo(expectedProductsInOrder);

        verify(cartRepository).findById(cartId);
        verify(cartRepository).deleteById(cartId);
    }
}