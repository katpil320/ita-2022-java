package sk.martinliptak.ita.rest;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import sk.martinliptak.ita.domain.Author;
import sk.martinliptak.ita.model.AuthorDto;
import sk.martinliptak.ita.repository.AuthorRepository;

import java.util.Arrays;

import static sk.martinliptak.ita.mother.AuthorMother.prepareAuthor;
import static sk.martinliptak.ita.mother.AuthorMother.prepareAuthorDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthorControllerIT implements WithAssertions {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void findAllAuthors() {
        Author author = prepareAuthor();
        AuthorDto authorDto = prepareAuthorDto();
        author.setId(null);
        authorRepository.save(author);
        authorDto.setId(author.getId());

        ResponseEntity<AuthorDto[]> response = testRestTemplate.getForEntity("/api/v1/authors", AuthorDto[].class);

        AuthorDto[] body = response.getBody();

        assertThat(body).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Arrays.stream(body).toList()).contains(authorDto);
    }
}