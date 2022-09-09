package sk.martinliptak.ita.mapper;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import sk.martinliptak.ita.domain.Product;
import sk.martinliptak.ita.model.ProductRequestDto;
import sk.martinliptak.ita.model.ProductDto;
import sk.martinliptak.ita.model.ProductSimpleDto;

@Mapper
public interface ProductMapper {
    @BeforeMapping
    default void beforeMapping(@MappingTarget ProductDto target, Product source) {
        target.setPreview(source.getPreview_file_name() != null);
    }

    @Mapping(target = "preview", ignore = true)
    ProductDto toDto(Product domain);
    ProductSimpleDto toSimpleDto(Product domain);
    Product toDomain(ProductRequestDto dto);
    void mergeProduct(@MappingTarget Product target, ProductRequestDto source);
}
