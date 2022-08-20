package sk.martinliptak.ita.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.martinliptak.ita.domain.Product;
import sk.martinliptak.ita.exception.AuthorNotFoundException;
import sk.martinliptak.ita.exception.GenreNotFoundException;
import sk.martinliptak.ita.exception.ProductNotFoundException;
import sk.martinliptak.ita.mapper.ProductMapper;
import sk.martinliptak.ita.model.ProductDto;
import sk.martinliptak.ita.model.ProductRequestDto;
import sk.martinliptak.ita.model.ProductSimpleDto;
import sk.martinliptak.ita.repository.AuthorRepository;
import sk.martinliptak.ita.repository.GenreRepository;
import sk.martinliptak.ita.repository.ProductRepository;
import sk.martinliptak.ita.service.ProductService;

import java.util.Collection;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    private final ProductMapper productMapper;

    @Transactional(readOnly = true)
    public ProductDto findProduct(Long id) {
        log.info("Fetching product {}", id);
        return productRepository.findById(id)
                .map(productMapper::toDto)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<ProductSimpleDto> findAllProducts() {
        log.info("Fetching all products");
        return productRepository.findAll()
                .stream().map(productMapper::toSimpleDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductDto createProduct(ProductRequestDto requestDto) {
        log.info("Creating product");
        Long authorId = requestDto.getAuthorId();
        Long genreId = requestDto.getGenreId();

        Product product = productMapper.toDomain(requestDto);

        product.setAuthor(authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId)));
        product.setGenre(genreRepository.findById(genreId)
                .orElseThrow(() -> new GenreNotFoundException(genreId)));

        productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    @Transactional
    public ProductDto updateProduct(ProductRequestDto requestDto, Long id) {
        log.info("Updating product {}", id);
        Long authorId = requestDto.getAuthorId();
        Long genreId = requestDto.getGenreId();

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        product.setAuthor(authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId)));
        product.setGenre(genreRepository.findById(genreId)
                .orElseThrow(() -> new GenreNotFoundException(genreId)));

        productMapper.mergeProduct(product, requestDto);
        return productMapper.toDto(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        log.info("Deleting product {}", id);
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        } else {
            productRepository.deleteById(id);
        }
    }
}
