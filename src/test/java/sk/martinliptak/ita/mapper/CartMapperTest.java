package sk.martinliptak.ita.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import sk.martinliptak.ita.domain.Cart;
import sk.martinliptak.ita.model.CartDto;

import static org.assertj.core.api.Assertions.assertThat;
import static sk.martinliptak.ita.mother.CartMother.prepareCart;
import static sk.martinliptak.ita.mother.CartMother.prepareCartDto;

class CartMapperTest {
    private final CartMapper cartMapper = Mappers.getMapper(CartMapper.class);

    @Test
    void toDto() {
        Cart source = prepareCart();
        CartDto expectedResult = prepareCartDto();
        CartDto result = cartMapper.toDto(source);

        assertThat(result.getId()).isEqualTo(source.getId());
        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }
}