package sk.martinliptak.ita.service;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sk.martinliptak.ita.domain.Genre;
import sk.martinliptak.ita.mapper.GenreMapper;
import sk.martinliptak.ita.model.GenreDto;
import sk.martinliptak.ita.repository.GenreRepository;
import sk.martinliptak.ita.service.impl.GenreServiceImpl;

import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static sk.martinliptak.ita.mother.GenreMother.*;

@ExtendWith(MockitoExtension.class)
class GenreServiceTest implements WithAssertions {
    @InjectMocks
    private GenreServiceImpl genreService;
    @Mock
    private GenreRepository genreRepository;
    @Mock
    private GenreMapper genreMapper;

    @Test
    void findAllGenres() {
        Genre genre = prepareGenre();
        Genre genre1 = prepareGenre1();
        GenreDto genreDto = prepareGenreDto();
        GenreDto genreDto1 = prepareGenreDto1();

        Collection<GenreDto> expectedResult = List.of(genreDto, genreDto1);

        when(genreRepository.findAll()).thenReturn(List.of(genre, genre1));
        when(genreMapper.toDto(genre)).thenReturn(genreDto);
        when(genreMapper.toDto(genre1)).thenReturn(genreDto1);

        Collection<GenreDto> result = genreService.findAllGenres();

        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);

        verify(genreRepository).findAll();
        verify(genreMapper).toDto(genre);
        verify(genreMapper).toDto(genre1);
    }
}