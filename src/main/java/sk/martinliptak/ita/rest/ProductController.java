package sk.martinliptak.ita.rest;

import com.amazonaws.services.s3.Headers;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sk.martinliptak.ita.model.ProductPreviewResponse;
import sk.martinliptak.ita.model.ProductRequestDto;
import sk.martinliptak.ita.model.ProductDto;
import sk.martinliptak.ita.model.ProductSimpleDto;
import sk.martinliptak.ita.service.ProductService;

import javax.servlet.http.HttpServletResponse;
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
        return productService.findProduct(id);
    }

    @GetMapping
    public Collection<ProductSimpleDto> findAllProducts() {
        return productService.findAllProducts();
    }

    @PostMapping
    public ProductDto createProduct(@Valid @RequestBody ProductRequestDto requestDto) {
        return productService.createProduct(requestDto);
    }

    @PostMapping(value = "{id}/preview", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addPreview(@PathVariable("id") Long id, @RequestPart("file") MultipartFile file) {
        productService.addPreview(id, file);
    }

    @GetMapping(value = "{id}/preview", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPreview(@PathVariable("id") Long id, HttpServletResponse response) {
        ProductPreviewResponse productPreviewResponse = productService.getPreview(id);
        response.addHeader(Headers.CONTENT_DISPOSITION, "attachment; filename=" + productPreviewResponse.getFilename());
        return productPreviewResponse.getBytes();
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
