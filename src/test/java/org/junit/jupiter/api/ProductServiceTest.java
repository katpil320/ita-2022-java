package org.junit.jupiter.api;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sk.martinliptak.ita.model.ProductDto;
import sk.martinliptak.ita.service.ProductService;

import javax.annotation.PostConstruct;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductServiceTest {

    ProductService productService = new ProductService();

    @BeforeAll
    void init() {
        productService.init();
    }
    
    @Test
    void getById() {
        assertEquals(2L, productService.getById(2L).getId());
    }

    @Test
    void getAll() {
        assertEquals(3, productService.getAll().size());
    }
}