package sk.martinliptak.ita.service.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sk.martinliptak.ita.client.ItaWarehouseClient;
import sk.martinliptak.ita.configuration.AmazonConfig;
import sk.martinliptak.ita.domain.Product;
import sk.martinliptak.ita.exception.AuthorNotFoundException;
import sk.martinliptak.ita.exception.FileNotReadableException;
import sk.martinliptak.ita.exception.GenreNotFoundException;
import sk.martinliptak.ita.exception.ProductNotFoundException;
import sk.martinliptak.ita.mapper.ProductMapper;
import sk.martinliptak.ita.model.ProductDto;
import sk.martinliptak.ita.model.ProductPreviewResponse;
import sk.martinliptak.ita.model.ProductRequestDto;
import sk.martinliptak.ita.model.ProductSimpleDto;
import sk.martinliptak.ita.repository.AuthorRepository;
import sk.martinliptak.ita.repository.GenreRepository;
import sk.martinliptak.ita.repository.ProductRepository;
import sk.martinliptak.ita.service.ProductService;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final ProductMapper productMapper;
    private final AmazonS3 amazonS3;
    private final AmazonConfig amazonConfig;
    private final ItaWarehouseClient itaWarehouseClient;

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
        return productRepository.findAll().stream()
                .map(productMapper::toSimpleDto)
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

    @Override
    @Transactional
    public void addPreview(Long id, MultipartFile file) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        String filename = product.getId() + "_" + file.getOriginalFilename();

        try {
            amazonS3.putObject(amazonConfig.getBucketName(),
                    filename,
                    file.getInputStream(),
                    new ObjectMetadata());
        } catch (IOException e) {
            throw new FileNotReadableException();
        }

        if (product.getPreview_file_name() != null) {
            amazonS3.deleteObject(amazonConfig.getBucketName(), product.getPreview_file_name());
        }

        product.setPreview_file_name(filename);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductPreviewResponse getPreview(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        String filename = product.getPreview_file_name();

        try {
            S3Object object = amazonS3.getObject(amazonConfig.getBucketName(), filename);
            S3ObjectInputStream objectContent = object.getObjectContent();
            return new ProductPreviewResponse()
                    .setFilename(filename)
                    .setBytes(IOUtils.toByteArray(objectContent));
        } catch (AmazonServiceException | IOException e) {
            throw new FileNotReadableException();
        }
    }

    @Override
    @Transactional
    public void updateStockFromWarehouse() {
        List<Product> products = productRepository.findAll();
        for (Product p : products) {
            p.setStock(itaWarehouseClient.getStock(p.getId()));
        }
    }
}
