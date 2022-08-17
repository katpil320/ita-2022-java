package sk.martinliptak.ita.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class AuthorDto {
    private Long id;
    private String name;
    private String bio;
    private LocalDate birthDate;
}