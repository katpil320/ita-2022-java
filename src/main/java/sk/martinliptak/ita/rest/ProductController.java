package sk.martinliptak.ita.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sk.martinliptak.ita.model.ProductRequestDto;
import sk.martinliptak.ita.model.ProductDto;
import sk.martinliptak.ita.model.ProductSimpleDto;
import sk.martinliptak.ita.service.ProductService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("api/v1/products")
@CrossOrigin("http://localhost:8088")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("{id}")
    public ProductDto findProduct(@PathVariable("id") Long id) {
        return productService.getById(id);
    }

    @GetMapping
    public Collection<ProductSimpleDto> findAllProducts() {
        return productService.findAll();
    }

    @PostMapping
    public ProductDto createProduct(@Valid @RequestBody ProductRequestDto requestDto) {
        return productService.createProduct(requestDto);
    }

    @PutMapping("{id}")
    public ProductDto updateProduct(@Valid @RequestBody ProductRequestDto productDto, @PathVariable("id") Long id) {
        return productService.updateProduct(productDto, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }
}
