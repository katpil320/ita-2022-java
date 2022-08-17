package sk.martinliptak.ita.service;

import sk.martinliptak.ita.model.ProductRequestDto;
import sk.martinliptak.ita.model.ProductDto;
import sk.martinliptak.ita.model.ProductSimpleDto;

import java.util.Collection;


public interface ProductService {
    ProductDto getById(Long id);
    Collection<ProductSimpleDto> findAll();

    ProductDto createProduct(ProductRequestDto productDto);

    ProductDto updateProduct(ProductRequestDto productDto, Long id);

    void deleteProduct(Long id);
}
