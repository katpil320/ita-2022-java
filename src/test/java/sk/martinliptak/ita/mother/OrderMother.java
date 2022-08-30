package sk.martinliptak.ita.mother;

import sk.martinliptak.ita.domain.Order;
import sk.martinliptak.ita.domain.OrderStatus;
import sk.martinliptak.ita.model.OrderDto;

import java.util.Set;

import static sk.martinliptak.ita.mother.ProductMother.*;

public class OrderMother {
    public static Order prepareOrder() {
        Order order = new Order()
                .setProducts(Set.of(prepareProduct(), prepareProduct1()))
                .setStatus(OrderStatus.NEW);
        order.setId(1L);
        return order;
    }
    public static OrderDto prepareOrderDto() {
        return new OrderDto()
                .setId(1L)
                .setProducts(Set.of(prepareProductSimpleDto(), prepareProductSimpleDto1()))
                .setStatus(OrderStatus.NEW);
    }
}
