package sk.martinliptak.ita.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sk.martinliptak.ita.model.ProductDto;
import sk.martinliptak.ita.service.ProductService;
import java.util.Collection;

@RestController
@RequestMapping("api/v1/products")
@CrossOrigin("http://ita-frontend.s3-website.eu-central-1.amazonaws.com")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public Collection<ProductDto> getAllProducts() {
        return productService.getAll();
    }

    @GetMapping("{id}")
    public ProductDto getProduct(@PathVariable("id") Long id) {
        return productService.getById(id);
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @PutMapping("{id}")
    public ProductDto updateProduct(@RequestBody ProductDto productDto, @PathVariable("id") Long id) {
        return productService.updateProduct(productDto, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }
}
