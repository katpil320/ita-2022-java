package sk.martinliptak.ita.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import sk.martinliptak.ita.domain.Product;
import sk.martinliptak.ita.model.ProductRequestDto;
import sk.martinliptak.ita.model.ProductDto;
import sk.martinliptak.ita.model.ProductSimpleDto;

@Mapper
public interface ProductMapper {
    ProductDto toDto(Product domain);
    ProductSimpleDto toSimpleDto(Product domain);
    Product toDomain(ProductRequestDto dto);
    void mergeProduct(@MappingTarget Product target, ProductRequestDto source);
}
