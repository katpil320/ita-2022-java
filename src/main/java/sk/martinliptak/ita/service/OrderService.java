package sk.martinliptak.ita.service;

import sk.martinliptak.ita.domain.OrderStatus;
import sk.martinliptak.ita.model.OrderDto;

import java.util.List;


/**
 * A service for manipulating orders
 * @see sk.martinliptak.ita.domain.Order
 */
public interface OrderService {
    /**
     * Creates new order upon given cart.
     * All new orders have {@link OrderStatus}.NEW.
     * @param cartId
     * @return {@link List} of {@link OrderDto}
     * @throws sk.martinliptak.ita.exception.CartNotFoundException if the cart does not exist
     */
    OrderDto createOrder(Long cartId);

    /**
     * Retrieves all orders from database
     * @return {@link List} of {@link OrderDto}
     */
    List<OrderDto> findAllOrders();

    /**
     * Updates status of specific order.
     * @param orderId
     * @param status to update Order status
     * @return {@link OrderDto}
     * @throws sk.martinliptak.ita.exception.OrderNotFoundException if the order does not exist
     */
    OrderDto updateStatus(Long orderId, OrderStatus status);
}
