package sk.martinliptak.ita.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "eshop_order")
public class Order extends AbstractEntity {
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "eshop_order_products",
            joinColumns = @JoinColumn(name = "fk_eshop_order"),
            inverseJoinColumns = @JoinColumn(name = "fk_product"))
    private Set<Product> products;
}
