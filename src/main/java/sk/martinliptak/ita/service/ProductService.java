package sk.martinliptak.ita.service;

import sk.martinliptak.ita.model.CreateProductRequestDTO;
import sk.martinliptak.ita.model.ProductDto;

import java.util.Collection;


public interface ProductService {
    ProductDto getById(Long id);
    Collection<ProductDto> getAll();

    ProductDto createProduct(CreateProductRequestDTO productDto);

    ProductDto updateProduct(CreateProductRequestDTO productDto, Long id);

    void deleteProduct(Long id);
}
