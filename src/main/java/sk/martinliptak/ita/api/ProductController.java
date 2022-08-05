package sk.martinliptak.ita.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sk.martinliptak.ita.model.CreateProductRequestDTO;
import sk.martinliptak.ita.model.ProductDto;
import sk.martinliptak.ita.service.ProductService;

import javax.servlet.http.HttpServletRequest;
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
    public ProductDto createProduct(HttpServletRequest request, @Valid @RequestBody CreateProductRequestDTO productDto) {
        return productService.createProduct(request, productDto);
    }

    @PutMapping("{id}")
    public ProductDto updateProduct(HttpServletRequest request, @Valid @RequestBody CreateProductRequestDTO productDto, @PathVariable("id") Long id) {
        return productService.updateProduct(request,productDto, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }
}
