package sk.martinliptak.ita.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.martinliptak.ita.domain.Product;
import sk.martinliptak.ita.exception.ProductNotFoundException;
import sk.martinliptak.ita.mapper.ProductMapper;
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
    private final ProductMapper productMapper;

    @Override
    @Transactional(readOnly = true)
    public ProductDto getById(Long id) {
        log.info("Fetching product({})", id);
        return productRepository.findById(id)
                .map(productMapper::toDto)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<ProductDto> getAll() {
        log.info("Fetching all products");
        return productRepository.findAll()
                .stream().map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductDto createProduct(CreateProductRequestDto productDto) {
        log.debug(incomingPayloadLogPattern, request.getMethod(), request.getRequestURI(), productDto);
        log.info("Creating product");
        Product product = productRepository.save(productMapper.toDomain(productDto));
        log.debug("Created new product - {}", product);
        return productMapper.toDto(product);
    }

    @Override
    @Transactional
    public ProductDto updateProduct(CreateProductRequestDto productDto, Long id) {
        log.debug(incomingPayloadLogPattern, request.getMethod(), request.getRequestURI(), productDto);
        log.info("Updating product({})", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        productMapper.mergeProduct(product, productDto);
        log.debug("Product({}) updated - {}", id, product);
        return productMapper.toDto(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        log.info("Deleting product({})", id);
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        } else {
            productRepository.deleteById(id);
        }
    }
}
