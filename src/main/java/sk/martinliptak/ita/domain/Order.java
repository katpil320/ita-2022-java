package sk.martinliptak.ita.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "eshop_order")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Order extends AbstractEntity {
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToMany
    private Set<Product> products;
}
