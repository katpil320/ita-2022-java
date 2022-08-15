package sk.martinliptak.ita.model;

import lombok.*;
import lombok.experimental.Accessors;
import sk.martinliptak.ita.annotation.StartsWithUppercase;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class CreateProductRequestDto {
    @NotBlank
    @Size(max = 256)
    @StartsWithUppercase
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
