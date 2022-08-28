package sk.martinliptak.ita.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Cart extends AbstractEntity {
    @ManyToMany
    @JoinTable(
            name = "cart_products",
            joinColumns = @JoinColumn(name = "fk_cart"),
            inverseJoinColumns = @JoinColumn(name = "fk_product"))
    private Set<Product> products;
}
