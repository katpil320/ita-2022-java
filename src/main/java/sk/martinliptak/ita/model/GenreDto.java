package sk.martinliptak.ita.model;

import lombok.*;

@Data
@NoArgsConstructor
public class GenreDto {
    private Long id;
    private String name;
    private String description;
}
