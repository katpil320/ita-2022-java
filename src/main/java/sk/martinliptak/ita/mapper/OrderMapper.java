package sk.martinliptak.ita.mapper;

import org.mapstruct.Mapper;
import sk.martinliptak.ita.domain.Order;
import sk.martinliptak.ita.model.OrderDto;

@Mapper
public interface OrderMapper {
    OrderDto toDto(Order domain);
}
