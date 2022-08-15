package sk.martinliptak.ita.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import sk.martinliptak.ita.domain.Product;
import sk.martinliptak.ita.exception.ProductNotFoundException;
import sk.martinliptak.ita.mapper.ProductMapper;
import sk.martinliptak.ita.model.CreateProductRequestDto;
import sk.martinliptak.ita.model.ProductDto;
import sk.martinliptak.ita.repository.ProductRepository;
import sk.martinliptak.ita.service.impl.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;
import static sk.martinliptak.ita.mapper.ProductAssertions.*;
import static sk.martinliptak.ita.mother.ProductMother.*;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private HttpServletRequest request;

    @Spy
    private ProductMapper productMapper;

    @Test
    void getById() {
        Product product = prepareProduct();
        ProductDto expectedResult = prepareProductDto();

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productMapper.toDto(product)).thenReturn(expectedResult);

        ProductDto result = productService.getById(1L);

        assertThat(result.getId()).isEqualTo(expectedResult.getId());
        assertFieldsEquality(result, expectedResult);

        verify(productRepository).findById(1L);
        verify(productMapper).toDto(product);
    }

    @Test
    void getAll() {
        Product product = prepareProduct();
        Product product1 = prepareProduct1();
        ProductDto productDto = prepareProductDto();
        ProductDto productDto1 = prepareProductDto1();

        when(productRepository.findAll()).thenReturn(List.of(product, product1));
        when(productMapper.toDto(product)).thenReturn(productDto);
        when(productMapper.toDto(product1)).thenReturn(productDto1);

        List<ProductDto> result = productService.getAll().stream().toList();
        assertThat(result.size()).isEqualTo(2);
        assertFieldsEquality(result.get(0), product);
        assertFieldsEquality(result.get(1), product1);

        verify(productRepository).findAll();
        verify(productMapper).toDto(product);
        verify(productMapper).toDto(product1);
    }

    @Test
    void createProduct() {
        CreateProductRequestDto createDto = prepareCreateProductDto();
        Product product = prepareProduct();
        ProductDto expectedResult = prepareProductDto();

        when(productMapper.toDomain(createDto)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.toDto(product)).thenReturn(expectedResult);

        ProductDto result = productService.createProduct(createDto);

        assertFieldsEquality(createDto, result);

        verify(productRepository, times(1)).save(product);
        verify(productMapper).toDto(product);
        verify(productMapper).toDomain(createDto);
    }

    @Test
    void updateProduct() {
        Product product = prepareProduct();
        ProductDto productDto = prepareProductDto();
        Long targetId = 1L;
        CreateProductRequestDto createDto = prepareCreateProductDto1();

        when(productRepository.findById(targetId)).thenReturn(Optional.of(product));
        when(productMapper.toDto(product)).thenReturn(productDto);
        ProductDto result = productService.updateProduct(createDto, targetId);

        assertThat(result.getId()).isEqualTo(targetId);

        verify(productMapper).toDto(product);
        verify(productMapper).mergeProduct(product, createDto);
    }

    @Test
    void deleteProduct() {
        Long targetId = 1L;

        when(productRepository.existsById(targetId)).thenReturn(true);

        productService.deleteProduct(targetId);

        verify(productRepository).existsById(targetId);
        verify(productRepository).deleteById(targetId);
    }

    @Test()
    void whenProductNotFound() throws ProductNotFoundException {
        CreateProductRequestDto createProductDto = prepareCreateProductDto();

        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        when(productRepository.existsById(1L)).thenReturn(false);

        assertThatThrownBy(() -> productService.getById(1L)).isInstanceOf(ProductNotFoundException.class);
        assertThatThrownBy(() -> productService.updateProduct(createProductDto, 1L)).isInstanceOf(ProductNotFoundException.class);
        assertThatThrownBy(() -> productService.deleteProduct(1L)).isInstanceOf(ProductNotFoundException.class);

        verify(productRepository, times(2)).findById(1L);
        verify(productRepository).existsById(1L);
    }
}
