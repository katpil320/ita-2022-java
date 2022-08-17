package sk.martinliptak.ita.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class ProductSimpleDto {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String image;
    private Long price;
}
