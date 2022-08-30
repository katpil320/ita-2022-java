package sk.martinliptak.ita.rest;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import sk.martinliptak.ita.domain.Author;
import sk.martinliptak.ita.domain.Genre;
import sk.martinliptak.ita.domain.Product;
import sk.martinliptak.ita.model.ProductDto;
import sk.martinliptak.ita.model.ProductRequestDto;
import sk.martinliptak.ita.repository.AuthorRepository;
import sk.martinliptak.ita.repository.GenreRepository;
import sk.martinliptak.ita.repository.ProductRepository;

import static sk.martinliptak.ita.mother.AuthorMother.*;
import static sk.martinliptak.ita.mother.GenreMother.prepareGenre;
import static sk.martinliptak.ita.mother.ProductMother.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerIT implements WithAssertions {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private GenreRepository genreRepository;

    private final String adminAuthToken = "Basic YWRtaW46cGFzc3dvcmQ=";

    @BeforeEach
    void clean() {
        productRepository.deleteAll();
        authorRepository.deleteAll();
        genreRepository.deleteAll();
    }

    @Test
    void findProduct() {
        Product product = prepareProduct();
        Author author = product.getAuthor();
        Genre genre = product.getGenre();
        ProductDto productDto = prepareProductDto();
        author.setId(null);
        genre.setId(null);
        product.setId(null);
        product.getAuthor().setId(author.getId());
        product.getGenre().setId(genre.getId());
        authorRepository.save(author);
        genreRepository.save(genre);
        productRepository.save(product);

        ResponseEntity<ProductDto> response = testRestTemplate.getForEntity("/api/v1/products/" + product.getId(), ProductDto.class);

        ProductDto body = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(body).isNotNull();
        assertThat(body).usingRecursiveComparison().ignoringFieldsMatchingRegexes("^.*id$").isEqualTo(productDto);
        assertThat(body.getId()).isEqualTo(product.getId());
        assertThat(body.getAuthor().getId()).isEqualTo(author.getId());
        assertThat(body.getGenre().getId()).isEqualTo(genre.getId());
    }

    @Test
    void createProduct() {
        ProductRequestDto productRequestDto = prepareProductRequestDto();
        Author author = prepareAuthor();
        Genre genre = prepareGenre();
        author.setId(null);
        genre.setId(null);
        authorRepository.save(author);
        genreRepository.save(genre);
        productRequestDto.setAuthorId(author.getId());
        productRequestDto.setGenreId(genre.getId());
        ProductDto expectedDto = prepareProductDto();
        expectedDto.getAuthor().setId(author.getId());
        expectedDto.getGenre().setId(genre.getId());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, adminAuthToken);
        HttpEntity<ProductRequestDto> request = new HttpEntity<>(productRequestDto, headers);

        ResponseEntity<ProductDto> response = testRestTemplate.postForEntity("/api/v1/products", request, ProductDto.class);

        ProductDto body = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(body).isNotNull();
        assertThat(body).usingRecursiveComparison().ignoringFields("id").isEqualTo(expectedDto);
    }

    @Test
    void updateProduct() {
        // Changing name and author
        Product product = prepareProduct();
        Author author = product.getAuthor();
        Author author1 = prepareAuthor1();
        Genre genre = product.getGenre();
        product.setId(null);
        author.setId(null);
        author1.setId(null);
        genre.setId(null);
        authorRepository.save(author);
        authorRepository.save(author1);
        genreRepository.save(genre);
        productRepository.save(product);

        ProductRequestDto productRequestDto = prepareProductRequestDto();
        productRequestDto.setAuthorId(author1.getId());
        productRequestDto.setGenreId(genre.getId());
        productRequestDto.setName("Zmenil sa na nazov?");

        ProductDto expectedResult = prepareProductDto();
        expectedResult.setAuthor(prepareAuthorDto1());
        expectedResult.getAuthor().setId(author1.getId());
        expectedResult.getGenre().setId(genre.getId());
        expectedResult.setId(product.getId());
        expectedResult.setName("Zmenil sa na nazov?");

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, adminAuthToken);
        HttpEntity<Object> request = new HttpEntity<>(productRequestDto, headers);

        ResponseEntity<ProductDto> response = testRestTemplate.exchange("/api/v1/products/" + product.getId(), HttpMethod.PUT, request, ProductDto.class);

        ProductDto body = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(body).isNotNull();
        assertThat(body).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @Test
    void deleteProduct() {
        Product product = prepareProduct();
        Author author = product.getAuthor();
        Genre genre = product.getGenre();
        author.setId(null);
        genre.setId(null);
        product.setId(null);
        product.getAuthor().setId(author.getId());
        product.getGenre().setId(genre.getId());
        authorRepository.save(author);
        genreRepository.save(genre);
        productRepository.save(product);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, adminAuthToken);
        HttpEntity<Object> request = new HttpEntity<>(headers);

        ResponseEntity<Void> response = testRestTemplate.exchange("/api/v1/products/" + product.getId(), HttpMethod.DELETE, request, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}