package sk.martinliptak.ita.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CartDto {
    private Long id;
    private Set<ProductSimpleDto> products;
}
