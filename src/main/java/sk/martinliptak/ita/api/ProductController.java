package sk.martinliptak.ita.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.martinliptak.ita.model.ProductDto;
import sk.martinliptak.ita.service.ProductService;

import java.util.Collection;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Collection<ProductDto> getAllProducts() {
        return productService.findAllProducts();
    }

    @GetMapping("{id}")
    public ProductDto getProduct(@PathVariable("id") Long id) {
        return productService.findProduct(id);
    }
}
