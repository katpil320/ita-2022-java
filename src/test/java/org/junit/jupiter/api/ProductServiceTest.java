package org.junit.jupiter.api;

import sk.martinliptak.ita.service.ProductService;
import static org.assertj.core.api.Assertions.*;

class ProductServiceTest {

    ProductService productService = new ProductService();

    @BeforeEach
    void init() {
        productService.init();
    }

    @Test
    void getById() {
        assertThat(productService.getById(2L).getId()).isEqualTo(2L);
    }

    @Test
    void getAll() {
        assertThat(productService.getAll()).hasSize(3);
    }
}