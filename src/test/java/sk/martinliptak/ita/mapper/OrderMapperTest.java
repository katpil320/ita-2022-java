package sk.martinliptak.ita.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import sk.martinliptak.ita.domain.Order;
import sk.martinliptak.ita.model.OrderDto;

import static org.assertj.core.api.Assertions.assertThat;
import static sk.martinliptak.ita.mother.OrderMother.prepareOrder;
import static sk.martinliptak.ita.mother.OrderMother.prepareOrderDto;

class OrderMapperTest {
    private final OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    @Test
    void toDto() {
        Order source = prepareOrder();
        OrderDto expectedResult = prepareOrderDto();
        OrderDto result = orderMapper.toDto(source);

        assertThat(result.getId()).isEqualTo(source.getId());
        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }
}