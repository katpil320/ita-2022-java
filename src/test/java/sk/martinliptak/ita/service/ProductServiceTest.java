package sk.martinliptak.ita.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import sk.martinliptak.ita.domain.Author;
import sk.martinliptak.ita.domain.Genre;
import sk.martinliptak.ita.domain.Product;
import sk.martinliptak.ita.exception.ProductNotFoundException;
import sk.martinliptak.ita.mapper.ProductMapper;
import sk.martinliptak.ita.model.ProductDto;
import sk.martinliptak.ita.model.ProductRequestDto;
import sk.martinliptak.ita.model.ProductSimpleDto;
import sk.martinliptak.ita.repository.AuthorRepository;
import sk.martinliptak.ita.repository.GenreRepository;
import sk.martinliptak.ita.repository.ProductRepository;
import sk.martinliptak.ita.service.impl.ProductServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;
import static sk.martinliptak.ita.mother.AuthorMother.prepareAuthor;
import static sk.martinliptak.ita.mother.GenreMother.prepareGenre;
import static sk.martinliptak.ita.mother.ProductMother.*;


@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private GenreRepository genreRepository;
    @Spy
    private ProductMapper productMapper;


    @Test
    void findProduct() {
        Product product = prepareProduct();
        ProductDto expectedResult = prepareProductDto();

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productMapper.toDto(product)).thenReturn(expectedResult);

        ProductDto result = productService.findProduct(1L);

        assertThat(result).isEqualTo(expectedResult);

        verify(productRepository).findById(1L);
        verify(productMapper).toDto(product);
    }

    @Test
    void findAllProducts() {
        Product product = prepareProduct();
        Product product1 = prepareProduct1();
        ProductSimpleDto productSimpleDto = prepareProductSimpleDto();
        ProductSimpleDto productSimpleDto1 = prepareProductSimpleDto1();

        when(productRepository.findAll()).thenReturn(List.of(product, product1));
        when(productMapper.toSimpleDto(product)).thenReturn(productSimpleDto);
        when(productMapper.toSimpleDto(product1)).thenReturn(productSimpleDto1);

        List<ProductSimpleDto> result = productService.findAllProducts().stream().toList();

        assertThat(result).contains(productSimpleDto, productSimpleDto1);

        verify(productRepository).findAll();
        verify(productMapper).toSimpleDto(product);
    }

    @Test
    void createProduct() {
        ProductRequestDto requestDto = prepareProductRequestDto();
        Product product = prepareProduct();
        Author author = prepareAuthor();
        Genre genre = prepareGenre();
        ProductDto expectedResult = prepareProductDto();

        when(productMapper.toDomain(requestDto)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.toDto(product)).thenReturn(expectedResult);
        when(authorRepository.findById(requestDto.getAuthorId())).thenReturn(Optional.of(author));
        when(genreRepository.findById(requestDto.getGenreId())).thenReturn(Optional.of(genre));

        ProductDto result = productService.createProduct(requestDto);

        assertThat(result).isEqualTo(expectedResult);

        verify(productRepository, times(1)).save(product);
        verify(productMapper).toDto(product);
        verify(productMapper).toDomain(requestDto);
    }

    @Test
    void updateProduct() {
        Product product = prepareProduct();
        ProductDto productDto = prepareProductDto();
        Author author = prepareAuthor();
        Genre genre = prepareGenre();
        Long targetId = 1L;
        ProductRequestDto requestDto = prepareProductRequestDto1();

        when(productRepository.findById(targetId)).thenReturn(Optional.of(product));
        when(productMapper.toDto(product)).thenReturn(productDto);
        when(authorRepository.findById(requestDto.getAuthorId())).thenReturn(Optional.of(author));
        when(genreRepository.findById(requestDto.getGenreId())).thenReturn(Optional.of(genre));

        ProductDto result = productService.updateProduct(requestDto, targetId);

        assertThat(result).isEqualTo(productDto);

        verify(productMapper).toDto(product);
        verify(productMapper).mergeProduct(product, requestDto);
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
        ProductRequestDto createProductDto = prepareProductRequestDto();

        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        when(productRepository.existsById(1L)).thenReturn(false);

        assertThatThrownBy(() -> productService.findProduct(1L)).isInstanceOf(ProductNotFoundException.class);
        assertThatThrownBy(() -> productService.updateProduct(createProductDto, 1L)).isInstanceOf(ProductNotFoundException.class);
        assertThatThrownBy(() -> productService.deleteProduct(1L)).isInstanceOf(ProductNotFoundException.class);

        verify(productRepository, times(2)).findById(1L);
        verify(productRepository).existsById(1L);
    }

    @Test
    void fetchingNonExistingProduct() throws ProductNotFoundException {
        Long id = 1L;

        when(productRepository.findById(id)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> productService.findProduct(id)).isInstanceOf(ProductNotFoundException.class);
        verify(productRepository).findById(id);
    }

    @Test
    void updatingNonExistingProduct() throws ProductNotFoundException {
        Long id = 1L;
        ProductRequestDto createProductDto = prepareProductRequestDto();

        when(productRepository.findById(id)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> productService.updateProduct(createProductDto, 1L)).isInstanceOf(ProductNotFoundException.class);
        verify(productRepository).findById(id);
    }

    @Test
    void deletingNonExistingProduct() throws ProductNotFoundException {
        Long id = 1L;
        when(productRepository.existsById(1L)).thenReturn(false);
        assertThatThrownBy(() -> productService.deleteProduct(id)).isInstanceOf(ProductNotFoundException.class);

        verify(productRepository).existsById(id);
    }
}
