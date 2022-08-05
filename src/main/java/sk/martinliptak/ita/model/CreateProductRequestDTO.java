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
public class CreateProductRequestDTO {
    @NotBlank(message = "Name must be included")
    @Size(max = 256)
    private String name;
    @NotBlank(message = "Description must be included")
    @Size(max = 512)
    private String description;
    @NotNull
    @Min(1)
    private Long price;
    @Min(0)
    private Long stock;
    @NotBlank(message = "Image must be included")
    private String image;
}
