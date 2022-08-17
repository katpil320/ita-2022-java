package sk.martinliptak.ita.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {
    private Long id;
    @NotBlank
    private String name;
    private String description;
    @NotBlank
    private String image;
    private Long price;
    private Long stock;

    private AuthorDto author;
    private GenreDto genre;
}
