package sk.martinliptak.ita.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sk.martinliptak.ita.model.CreateProductRequestDto;
import sk.martinliptak.ita.model.ProductDto;
import sk.martinliptak.ita.service.ProductService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("api/v1/products")
@CrossOrigin("http://localhost:8088")
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
    public ProductDto createProduct(@Valid @RequestBody CreateProductRequestDto productDto) {
        return productService.createProduct(productDto);
    }

    @PutMapping("{id}")
    public ProductDto updateProduct(@Valid @RequestBody CreateProductRequestDto productDto, @PathVariable("id") Long id) {
        return productService.updateProduct(productDto, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }
}
