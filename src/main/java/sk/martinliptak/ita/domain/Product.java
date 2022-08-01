package sk.martinliptak.ita.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data
@Accessors(chain = true)
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Size(max = 512)
    private String description;
    private Long price;
    private Long stock;
    private String image;
}
