package sk.martinliptak.ita.rest;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import sk.martinliptak.ita.domain.Genre;
import sk.martinliptak.ita.model.GenreDto;
import sk.martinliptak.ita.repository.GenreRepository;

import java.util.Arrays;

import static sk.martinliptak.ita.mother.GenreMother.prepareGenre;
import static sk.martinliptak.ita.mother.GenreMother.prepareGenreDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GenreControllerIT implements WithAssertions {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private GenreRepository genreRepository;

    @Test
    void findAllGenres() {
        Genre genre = prepareGenre();
        GenreDto genreDto = prepareGenreDto();
        genre.setId(null);
        genreRepository.save(genre);
        genreDto.setId(genre.getId());

        ResponseEntity<GenreDto[]> response = testRestTemplate.getForEntity("/api/v1/genres", GenreDto[].class);

        GenreDto[] body = response.getBody();

        assertThat(body).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Arrays.stream(body).toList()).contains(genreDto);
    }
}