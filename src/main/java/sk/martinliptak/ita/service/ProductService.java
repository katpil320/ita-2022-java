package sk.martinliptak.ita.service;

import org.springframework.web.multipart.MultipartFile;
import sk.martinliptak.ita.model.ProductPreviewResponse;
import sk.martinliptak.ita.model.ProductRequestDto;
import sk.martinliptak.ita.model.ProductDto;
import sk.martinliptak.ita.model.ProductSimpleDto;

import java.util.Collection;


/**
 * A service for manipulating products
 * @see sk.martinliptak.ita.domain.Product
 */
public interface ProductService {
    /**
     * Retrieves specific product from database
     * @param id of product
     * @return {@link ProductDto}
     * @throws sk.martinliptak.ita.exception.ProductNotFoundException if the product doesn't exist
     */
    ProductDto findProduct(Long id);

    /**
     * Retrieves all products from database
     * @see ProductSimpleDto
     * @return {@link java.util.List} of {@link ProductSimpleDto} if no product is present, returns empty list
     */
    Collection<ProductSimpleDto> findAllProducts();

    /**
     * Creates new product. Uses {@link ProductRequestDto} as param for cleaner json requests.
     * @param productDto ({@link ProductRequestDto})
     * @return {@link ProductDto}
     */
    ProductDto createProduct(ProductRequestDto productDto);

    /**
     * Updates product
     * @param productDto ({@link ProductRequestDto})
     * @param id of product
     * @return {@link ProductDto}
     * @throws sk.martinliptak.ita.exception.ProductNotFoundException if the product doesn't exist
     */
    ProductDto updateProduct(ProductRequestDto productDto, Long id);


    /**
     * Deletes product
     * @param id of product
     * @throws sk.martinliptak.ita.exception.ProductNotFoundException if the product doesn't exist
     */
    void deleteProduct(Long id);

    /**
     * Adds preview for product
     * @param id of product
     * @param file
     */
    void addPreview(Long id, MultipartFile file);
    /**
     * Retrieves preview of the product
     * @param id of product
     * @return {@link ProductPreviewResponse}
     */
    ProductPreviewResponse getPreview(Long id);

    /**
     * Updates all products stock from warehouse client
     */
    void updateStockFromWarehouse();
}
