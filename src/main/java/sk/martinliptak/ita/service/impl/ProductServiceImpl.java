package sk.martinliptak.ita.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sk.martinliptak.ita.domain.Product;
import sk.martinliptak.ita.exception.ProductNotFoundException;
import sk.martinliptak.ita.model.CreateProductRequestDto;
import sk.martinliptak.ita.model.ProductDto;
import sk.martinliptak.ita.repository.ProductRepository;
import sk.martinliptak.ita.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final HttpServletRequest request;
    private final String incomingPayloadLogPattern = "Incoming {} request on {}, payload={}";

    @Override
    public ProductDto getById(Long id) {
        log.info("Fetching product({})", id);
        return toDto(productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id)));
    }

    @Override
    public Collection<ProductDto> getAll() {
        log.info("Fetching all products");
        return productRepository.findAll()
                .stream().map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto createProduct(CreateProductRequestDto productDto) {
        log.debug(incomingPayloadLogPattern, request.getMethod(), request.getRequestURI(), productDto);
        log.info("Creating product");
        Product product = toProduct(productDto);
        Product savedProduct = productRepository.save(product);
        log.debug("Created new product - {}", savedProduct);
        return toDto(savedProduct);
    }

    @Override
    public ProductDto updateProduct(CreateProductRequestDto productDto, Long id) {
        log.debug(incomingPayloadLogPattern, request.getMethod(), request.getRequestURI(), productDto);
        log.info("Updating product({})", id);
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        } else {
            Product product = toProduct(productDto).setId(id);
            productRepository.save(product);
            log.debug("Product({}) updated - {}", id, product);
            return toDto(product);
        }
    }

    @Override
    public void deleteProduct(Long id) {
        log.info("Deleting product({})", id);
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        } else {
            productRepository.deleteById(id);
        }
    }

    private Product toProduct(CreateProductRequestDto productDto) {
        return new Product()
                .setName(productDto.getName())
                .setDescription(productDto.getDescription())
                .setImage(productDto.getImage())
                .setStock(productDto.getStock())
                .setPrice(productDto.getPrice());
    }


    private ProductDto toDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getImage()
        );
    }
}
