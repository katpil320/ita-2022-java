package sk.martinliptak.ita.mother;

import sk.martinliptak.ita.domain.Genre;
import sk.martinliptak.ita.model.GenreDto;

public class GenreMother {
    public static Genre prepareGenre() {
        Genre genre = new Genre()
                .setName("Drama")
                .setDescription("Super napinave dejstva");
        genre.setId(1L);
        return genre;
    }

    public static Genre prepareGenre1() {
        Genre genre = new Genre()
                .setName("Horror")
                .setDescription("Strasidelne veci");
        genre.setId(2L);
        return genre;
    }

    public static GenreDto prepareGenreDto() {
        return new GenreDto()
                .setId(1L)
                .setName("Drama")
                .setDescription("Super napinave dejstva");
    }

    public static GenreDto prepareGenreDto1() {
        return new GenreDto()
                .setId(2L)
                .setName("Horror")
                .setDescription("Strasidelne veci");
    }
}
