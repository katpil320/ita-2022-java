package sk.martinliptak.ita.service;

import sk.martinliptak.ita.model.ProductDto;

import java.util.Collection;


public interface ProductService {
    ProductDto getById(Long id);
    Collection<ProductDto> getAll();

    ProductDto createProduct(ProductDto productDto);

    ProductDto updateProduct(ProductDto productDto, Long id);

    void deleteProduct(Long id);
}
