package sk.martinliptak.ita.model;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class AuthorDto {
    private Long id;
    private String name;
    private String bio;
    private LocalDate birthDate;
}
