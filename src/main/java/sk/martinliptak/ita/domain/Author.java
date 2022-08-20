package sk.martinliptak.ita.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author extends AbstractEntity {
    private String name;
    private String bio;
    private LocalDate birthDate;
}
