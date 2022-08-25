package sk.martinliptak.ita.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import sk.martinliptak.ita.domain.OrderStatus;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderDto {
    private Long id;
    private Set<ProductSimpleDto> products;
    private OrderStatus status;
    private Instant createdAt;
}
