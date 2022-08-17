package sk.martinliptak.ita.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GenreDto {
    private Long id;
    private String name;
    private String description;
}
