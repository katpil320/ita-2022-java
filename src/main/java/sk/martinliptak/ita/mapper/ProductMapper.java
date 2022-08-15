package sk.martinliptak.ita.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import sk.martinliptak.ita.domain.Product;
import sk.martinliptak.ita.model.CreateProductRequestDto;
import sk.martinliptak.ita.model.ProductDto;

@Mapper
public interface ProductMapper {
    ProductDto toDto(Product domain);
    Product toDomain(CreateProductRequestDto dto);
    void mergeProduct(@MappingTarget Product target, CreateProductRequestDto source);
}
