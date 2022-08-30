package sk.martinliptak.ita.model;

import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
public class CartDto {
    private Long id;
    private Set<ProductSimpleDto> products;
}
