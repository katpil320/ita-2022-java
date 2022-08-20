package sk.martinliptak.ita.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import sk.martinliptak.ita.domain.Genre;
import sk.martinliptak.ita.model.GenreDto;

import static org.assertj.core.api.Assertions.assertThat;
import static sk.martinliptak.ita.mother.GenreMother.prepareGenre;
import static sk.martinliptak.ita.mother.GenreMother.prepareGenreDto;

class GenreMapperTest {
    private final GenreMapper genreMapper = Mappers.getMapper(GenreMapper.class);

    @Test
    void toDto() {
        Genre source = prepareGenre();
        GenreDto expectedResult = prepareGenreDto();
        GenreDto result = genreMapper.toDto(source);

        assertThat(result.getId()).isEqualTo(source.getId());
        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }
}