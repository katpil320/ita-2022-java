package sk.martinliptak.ita.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import sk.martinliptak.ita.domain.Product;
import sk.martinliptak.ita.model.ProductRequestDto;
import sk.martinliptak.ita.model.ProductDto;
import sk.martinliptak.ita.model.ProductSimpleDto;


import static org.assertj.core.api.Assertions.*;
import static sk.martinliptak.ita.mother.ProductMother.*;

class ProductMapperTest {
    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Test
    void toDto() {
        Product source = prepareProduct();
        ProductDto expectedResult = prepareProductDto();
        ProductDto result = productMapper.toDto(source);

        assertThat(result.getId()).isEqualTo(source.getId());
        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @Test
    void toSimpleDto() {
        Product source = prepareProduct();
        ProductSimpleDto expectedResult = prepareProductSimpleDto();
        ProductSimpleDto result = productMapper.toSimpleDto(source);

        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @Test
    void toDomain() {
        ProductRequestDto source = prepareProductRequestDto();
        Product expectedResult = prepareProduct();
        Product result = productMapper.toDomain(source);

        assertThat(result.getId()).isNull(); // ID will be generated by JPA
        assertThat(result).usingRecursiveComparison().ignoringFields("id", "genre", "author").isEqualTo(expectedResult);
    }

    @Test
    void mergeProduct() {
        ProductRequestDto source = prepareProductRequestDto1(); // A different dto
        Product target = prepareProduct(); // Being updated (Becomes result after merge)
        Product expectedResult = prepareProduct1(); // Expected

        productMapper.mergeProduct(target, source);

        assertThat(target.getId()).isEqualTo(target.getId()); // Because ID cannot change
        assertThat(target).usingRecursiveComparison().ignoringFields("id", "genre", "author").isEqualTo(expectedResult);
    }
}