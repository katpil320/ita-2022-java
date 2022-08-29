package sk.martinliptak.ita.model;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class ProductSimpleDto {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String image;
    private Long price;
}
