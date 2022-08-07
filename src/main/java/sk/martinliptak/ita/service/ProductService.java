package sk.martinliptak.ita.service;

import sk.martinliptak.ita.model.CreateProductRequestDto;
import sk.martinliptak.ita.model.ProductDto;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;


public interface ProductService {
    ProductDto getById(Long id);
    Collection<ProductDto> getAll();

    ProductDto createProduct(HttpServletRequest request, CreateProductRequestDto productDto);

    ProductDto updateProduct(HttpServletRequest request, CreateProductRequestDto productDto, Long id);

    void deleteProduct(Long id);
}
