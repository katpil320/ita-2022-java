package sk.martinliptak.ita.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private Set<ProductSimpleDto> products;
}