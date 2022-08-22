package sk.martinliptak.ita.model;

import lombok.*;
import sk.martinliptak.ita.annotation.StartsWithUppercase;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductRequestDto {
    @NotBlank
    @Size(max = 256)
    @StartsWithUppercase
    private String name;
    @NotBlank
    @Size(max = 512)
    private String description;
    @NotBlank
    private String image;
    @NotNull
    @Min(1)
    private Long price;
    @Positive
    private Long stock;

    @NotNull
    private Long authorId;
    @NotNull
    private Long genreId;
}
