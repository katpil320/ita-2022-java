package sk.martinliptak.ita.mapper;

import sk.martinliptak.ita.domain.Product;
import sk.martinliptak.ita.model.CreateProductRequestDto;
import sk.martinliptak.ita.model.ProductDto;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductAssertions {
    public static void assertFieldsEquality(Product p, Product p1) {
        assertThat(p.getName()).isEqualTo(p1.getName());
        assertThat(p.getDescription()).isEqualTo(p1.getDescription());
        assertThat(p.getPrice()).isEqualTo(p1.getPrice());
        assertThat(p.getStock()).isEqualTo(p1.getStock());
        assertThat(p.getImage()).isEqualTo(p1.getImage());
    }

    public static void assertFieldsEquality(ProductDto d, Product p) {
        assertThat(d.getName()).isEqualTo(p.getName());
        assertThat(d.getDescription()).isEqualTo(p.getDescription());
        assertThat(d.getPrice()).isEqualTo(p.getPrice());
        assertThat(d.getStock()).isEqualTo(p.getStock());
        assertThat(d.getImage()).isEqualTo(p.getImage());
    }
    public static void assertFieldsEquality(ProductDto d, ProductDto d1) {
        assertThat(d.getName()).isEqualTo(d1.getName());
        assertThat(d.getDescription()).isEqualTo(d1.getDescription());
        assertThat(d.getPrice()).isEqualTo(d1.getPrice());
        assertThat(d.getStock()).isEqualTo(d1.getStock());
        assertThat(d.getImage()).isEqualTo(d1.getImage());
    }
    public static void assertFieldsEquality(CreateProductRequestDto c, ProductDto d) {
        assertThat(c.getName()).isEqualTo(d.getName());
        assertThat(c.getDescription()).isEqualTo(d.getDescription());
        assertThat(c.getPrice()).isEqualTo(d.getPrice());
        assertThat(c.getStock()).isEqualTo(d.getStock());
        assertThat(c.getImage()).isEqualTo(d.getImage());
    }
}
