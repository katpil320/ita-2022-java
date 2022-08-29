package sk.martinliptak.ita.model;

import lombok.*;
import sk.martinliptak.ita.domain.OrderStatus;

import java.time.Instant;
import java.util.Set;

@Data
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private Set<ProductSimpleDto> products;
    private OrderStatus status;
    private Instant createdAt;
}
