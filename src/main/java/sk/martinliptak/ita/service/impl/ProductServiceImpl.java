package sk.martinliptak.ita.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sk.martinliptak.ita.domain.Product;
import sk.martinliptak.ita.model.CreateProductRequestDTO;
import sk.martinliptak.ita.model.ProductDto;
import sk.martinliptak.ita.repository.ProductRepository;
import sk.martinliptak.ita.service.ProductService;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final String productNotFoundMessage = "Cannot find product with given id = %s";

    // Logging patterns
    private final String incomingPayloadLogPattern = "Incoming {} request on {}, body={}";

    @Override
    public ProductDto getById(Long id) {
        log.info("Fetching product({})", id);
        return toDto(productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format(productNotFoundMessage, id))));
    }

    @Override
    public Collection<ProductDto> getAll() {
        log.info("Fetching all products");
        return productRepository.findAll()
                .stream().map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto createProduct(HttpServletRequest request, CreateProductRequestDTO productDto) {
        log.debug(incomingPayloadLogPattern, request.getMethod(), request.getRequestURI(), productDto);
        log.info("Creating product");
        Product product = toProduct(productDto);
        Product savedProduct = productRepository.save(product);
        log.debug("Created new product - {}", savedProduct);
        return toDto(savedProduct);
    }

    @Override
    public ProductDto updateProduct(HttpServletRequest request, CreateProductRequestDTO productDto, Long id) {
        log.debug(incomingPayloadLogPattern, request.getMethod(), request.getRequestURI(), productDto);
        log.info("Updating product({})", id);
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format(productNotFoundMessage, id));
        } else {
            Product product = productRepository.findById(id).get();
            // Update data
            product.setName(productDto.getName())
                    .setDescription(productDto.getDescription())
                    .setImage(productDto.getImage())
                    .setStock(product.getStock())
                    .setPrice(productDto.getPrice());
            log.debug("Product({}) updated - {}", id, product.toString());
            return toDto(productRepository.save(product));
        }
    }

    @Override
    public void deleteProduct(Long id) {
        log.info("Deleting product({})", id);
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format(productNotFoundMessage, id));
        } else {
            productRepository.deleteById(id);
        }
    }

    private Product toProduct(CreateProductRequestDTO productDto) {
        Product product = new Product()
                .setName(productDto.getName())
                .setDescription(productDto.getDescription())
                .setImage(productDto.getImage())
                .setStock(productDto.getStock())
                .setPrice(productDto.getPrice());
        return product;
    }


    private ProductDto toDto(Product product) {
        ProductDto productDto = new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getImage()
        );
        return productDto;
    }
}
