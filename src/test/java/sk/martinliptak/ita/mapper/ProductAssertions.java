package sk.martinliptak.ita.mapper;

import sk.martinliptak.ita.domain.Product;
import sk.martinliptak.ita.model.ProductDto;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductAssertions {
    static void assertFieldsEquality(Product p, Product p2) {
        assertThat(p.getName()).isEqualTo(p2.getName());
        assertThat(p.getDescription()).isEqualTo(p2.getDescription());
        assertThat(p.getPrice()).isEqualTo(p2.getPrice());
        assertThat(p.getStock()).isEqualTo(p2.getStock());
        assertThat(p.getImage()).isEqualTo(p2.getImage());
    }

    static void assertFieldsEquality(ProductDto d, ProductDto d2) {
        assertThat(d.getName()).isEqualTo(d2.getName());
        assertThat(d.getDescription()).isEqualTo(d2.getDescription());
        assertThat(d.getPrice()).isEqualTo(d2.getPrice());
        assertThat(d.getStock()).isEqualTo(d2.getStock());
        assertThat(d.getImage()).isEqualTo(d2.getImage());
    }
}
