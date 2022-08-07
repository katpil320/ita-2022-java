package sk.martinliptak.ita.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CreateProductRequestDto {
    @NotBlank
    @Size(max = 256)
    private String name;
    @NotBlank
    @Size(max = 512)
    private String description;
    @NotNull
    @Min(1)
    private Long price;
    @Positive
    private Long stock;
    @NotBlank
    private String image;
}
