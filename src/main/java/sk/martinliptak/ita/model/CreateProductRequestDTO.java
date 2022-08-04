package sk.martinliptak.ita.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CreateProductRequestDTO {
    private String name;
    private String description;
    private Long price;
    private Long stock;
    private String image;
}
