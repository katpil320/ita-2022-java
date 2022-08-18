package sk.martinliptak.ita.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@ToString
public class Product extends AbstractEntity {
    private String name;
    @Size(max = 512) private String description;
    private Long price;
    private Long stock;
    private String image;

    @ManyToOne
    private Genre genre;

    @ManyToOne
    private Author author;
}
