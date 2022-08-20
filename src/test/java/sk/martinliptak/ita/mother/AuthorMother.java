package sk.martinliptak.ita.mother;

import sk.martinliptak.ita.domain.Author;
import sk.martinliptak.ita.model.AuthorDto;

import java.time.LocalDate;

public class AuthorMother {
    public static Author prepareAuthor() {
        Author author = new Author()
                .setName("Peter Smutny")
                .setBio("Som zruteny")
                .setBirthDate(LocalDate.of(1956, 2, 3));
        author.setId(1L);
        return author;
    }

    public static Author prepareAuthor1() {
        Author author = new Author()
                .setName("Jozef Suprovy")
                .setBio("Super info o mne")
                .setBirthDate(LocalDate.of(1978, 5, 14));
        author.setId(2L);
        return author;
    }

    public static AuthorDto prepareAuthorDto() {
        return new AuthorDto()
                .setId(1L)
                .setName("Peter Smutny")
                .setBio("Som zruteny")
                .setBirthDate(LocalDate.of(1956, 2, 3));
    }

    public static AuthorDto prepareAuthorDto1() {
        return new AuthorDto()
                .setId(2L)
                .setName("Jozef Suprovy")
                .setBio("Super info o mne")
                .setBirthDate(LocalDate.of(1978, 5, 14));
    }
}
