package sk.martinliptak.ita.mapper;

import org.mapstruct.Mapper;
import sk.martinliptak.ita.domain.Cart;
import sk.martinliptak.ita.model.CartDto;

@Mapper
public interface CartMapper {
    CartDto toDto(Cart domain);
}
