package sk.martinliptak.ita.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sk.martinliptak.ita.domain.Product;
import sk.martinliptak.ita.model.ProductDto;
import sk.martinliptak.ita.repository.ProductRepository;
import sk.martinliptak.ita.service.ProductService;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final String productNotFoundMessage = "Cannot find product with given id = %s";

    @PostConstruct
    public void init() {
        Product product = new Product()
                .setName("Bozek stavitel")
                .setDescription("Kniha pro deti")
                .setStock(142L)
                .setPrice(32L)
                .setImage("https://www.houseland.sk/image/catalog/ruc-borek.jpg");

        Product product1 = new Product()
                .setName("Programovani v jazyku Java")
                .setDescription("Kniha pro programatory")
                .setStock(24L)
                .setPrice(300L)
                .setImage("https://data.bux.sk/book/064/213/0642136/large-zaciname_programovat_v_jazyku_java.jpg");
        productRepository.save(product);
        productRepository.save(product1);
    }

    @Override
    public ProductDto getById(Long id) {
        return toDto(productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(productNotFoundMessage, id))));
    }

    @Override
    public Collection<ProductDto> getAll() {
        return productRepository.findAll()
                .stream().map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = toProduct(productDto);
        Product savedProduct = productRepository.save(product);
        return toDto(savedProduct);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, Long id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format(productNotFoundMessage, id));
        }
        else {
            Product product = productRepository.findById(id).get();
            // Update data
            product.setName(productDto.getName())
                    .setDescription(productDto.getDescription())
                    .setImage(productDto.getImage())
                    .setStock(product.getStock())
                    .setPrice(productDto.getPrice());
            Product updatedProduct = productRepository.save(product);
            return toDto(updatedProduct);
        }
    };

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format(productNotFoundMessage, id));
        }
        else {
            productRepository.deleteById(id);
        }
    }

    private Product toProduct(ProductDto productDto) {
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
