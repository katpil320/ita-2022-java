package sk.martinliptak.ita.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;


@Entity
@Data
@NoArgsConstructor
public class Genre extends AbstractEntity {
    private String name;
    private String description;
}
