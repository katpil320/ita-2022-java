package sk.martinliptak.ita.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateProductRequestDTO {
    private String name;
    private String description;
    private Long price;
    private Long stock;
    private String image;
}
