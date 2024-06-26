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
@ToString
public class Product extends AbstractEntity {
    private String name;
    @Size(max = 512) private String description;
    private Long price;
    private Long stock;
    private String image;
    private String preview_file_name;

    @ManyToOne
    private Genre genre;

    @ManyToOne
    private Author author;
}
