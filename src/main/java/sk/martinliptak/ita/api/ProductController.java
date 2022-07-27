package sk.martinliptak.ita.api;

import org.springframework.web.bind.annotation.*;
import sk.martinliptak.ita.model.ProductDto;
import sk.martinliptak.ita.service.ProductService;

import java.util.Collection;

@RestController
@RequestMapping("api/v1/product")
@CrossOrigin("http://ita-frontend.s3-website.eu-central-1.amazonaws.com")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Collection<ProductDto> getAllProducts() {
        return productService.getAll();
    }

    @GetMapping("{id}")
    public ProductDto getProduct(@PathVariable("id") Long id) {
        return productService.getById(id);
    }
}
