package sk.martinliptak.ita.model;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
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
