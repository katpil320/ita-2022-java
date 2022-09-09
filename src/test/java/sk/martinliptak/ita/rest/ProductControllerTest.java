package sk.martinliptak.ita.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import sk.martinliptak.ita.exception.ProductNotFoundException;
import sk.martinliptak.ita.model.ProductDto;
import sk.martinliptak.ita.model.ProductRequestDto;
import sk.martinliptak.ita.model.ProductSimpleDto;
import sk.martinliptak.ita.service.impl.ProductServiceImpl;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sk.martinliptak.ita.mother.ProductMother.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest extends AbstractControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductServiceImpl productService;
    private final String adminAuthToken = "Basic YWRtaW46cGFzc3dvcmQ="; // Base 64

    @Test
    void findProduct() throws Exception {
        ProductDto productDto = prepareProductDto();
        when(productService.findProduct(1L)).thenReturn(productDto);
        mockMvc.perform(get("/api/v1/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(getJsonContent("/responses/products/findProduct.json")));
    }

    @Test
    void findProduct_notFound() throws Exception {
        when(productService.findProduct(1L)).thenThrow(new ProductNotFoundException(1L));
        mockMvc.perform(get("/api/v1/products/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().json(getJsonContent("/responses/products/findProduct_notFound.json")));

    }

    @Test
    void findAllProducts() throws Exception {
        List<ProductSimpleDto> productSimpleDtos = List.of(prepareProductSimpleDto(), prepareProductSimpleDto1());
        when(productService.findAllProducts()).thenReturn(productSimpleDtos);
        mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk())
                .andExpect(content().json(getJsonContent("/responses/products/findAllProducts.json")));
    }

    @Test
    void createProduct() throws Exception {
        ProductRequestDto productRequestDto = prepareProductRequestDto();
        ProductDto productDto = prepareProductDto();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, adminAuthToken);
        when(productService.createProduct(productRequestDto)).thenReturn(productDto);
        mockMvc.perform(post("/api/v1/products")
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getJsonContent("/requests/products/createProduct.json")))
                .andExpect(status().isOk())
                .andExpect(content().json(getJsonContent("/responses/products/createProduct.json")));
    }

    @Test
    void updateProduct() throws Exception {
        ProductRequestDto productRequestDto = prepareProductRequestDto();
        ProductDto productDto = prepareProductDto();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, adminAuthToken);
        when(productService.updateProduct(productRequestDto, 1L)).thenReturn(productDto);
        mockMvc.perform(put("/api/v1/products/1")
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getJsonContent("/requests/products/updateProduct.json")))
                .andExpect(status().isOk())
                .andExpect(content().json(getJsonContent("/responses/products/updateProduct.json")));
    }

    @Test
    void updateProduct_notFound() throws Exception {
        ProductRequestDto productRequestDto = prepareProductRequestDto();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, adminAuthToken);
        when(productService.updateProduct(productRequestDto, 1L)).thenThrow(new ProductNotFoundException(1L));
        mockMvc.perform(put("/api/v1/products/1")
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getJsonContent("/requests/products/updateProduct.json")))
                .andExpect(status().isNotFound())
                .andExpect(content().json(getJsonContent("/responses/products/updateProduct_notFound.json")));

    }

    @Test
    void deleteProduct() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, adminAuthToken);
        mockMvc.perform(delete("/api/v1/products/1")
                        .headers(headers))
                .andExpect(status().isNoContent());
    }
}