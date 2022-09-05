package sk.martinliptak.ita.rest;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import sk.martinliptak.ita.domain.Author;
import sk.martinliptak.ita.domain.Cart;
import sk.martinliptak.ita.domain.Genre;
import sk.martinliptak.ita.domain.Product;
import sk.martinliptak.ita.mapper.ProductMapper;
import sk.martinliptak.ita.model.CartDto;
import sk.martinliptak.ita.repository.AuthorRepository;
import sk.martinliptak.ita.repository.CartRepository;
import sk.martinliptak.ita.repository.GenreRepository;
import sk.martinliptak.ita.repository.ProductRepository;

import java.util.HashSet;
import java.util.Set;

import static sk.martinliptak.ita.mother.CartMother.prepareCart;
import static sk.martinliptak.ita.mother.ProductMother.prepareProduct;
import static sk.martinliptak.ita.mother.ProductMother.prepareProduct1;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CartControllerIT implements WithAssertions {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductMapper productMapper;

    @AfterEach
    void clean() {
        cartRepository.deleteAll();
        productRepository.deleteAll();
        authorRepository.deleteAll();
        genreRepository.deleteAll();
    }

    @Test
    void findCart() {
        Cart cart = prepareCart();
        Product product = prepareProduct();
        Author author = product.getAuthor();
        Genre genre = product.getGenre();
        product.setId(null);
        author.setId(null);
        genre.setId(null);
        cart.setId(null);
        cart.setProducts(new HashSet<>());
        product.getAuthor().setId(author.getId());
        product.getGenre().setId(genre.getId());
        authorRepository.save(author);
        genreRepository.save(genre);
        productRepository.save(product);
        cart.getProducts().add(product);
        cartRepository.save(cart);


        ResponseEntity<CartDto> response = testRestTemplate.getForEntity("/api/v1/carts/" + cart.getId(), CartDto.class);
        CartDto body = response.getBody();

        assertThat(body).isNotNull();
        assertThat(body.getProducts()).usingRecursiveComparison().isEqualTo(Set.of(productMapper.toSimpleDto(product)));
        assertThat(body).usingRecursiveComparison().isEqualTo(cart);
    }

    @Test
    void createCart() {
        Product product = prepareProduct();
        Author author = product.getAuthor();
        Genre genre = product.getGenre();
        product.setId(null);
        author.setId(null);
        genre.setId(null);
        product.getAuthor().setId(author.getId());
        product.getGenre().setId(genre.getId());
        authorRepository.save(author);
        genreRepository.save(genre);
        productRepository.save(product);

        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<CartDto> response = testRestTemplate.postForEntity("/api/v1/carts/products/" + product.getId(), headers, CartDto.class);

        CartDto body = response.getBody();

        assertThat(body).isNotNull();
        assertThat(body.getProducts()).usingRecursiveComparison().isEqualTo(Set.of(productMapper.toSimpleDto(product)));
    }

    @Test
    void addToCart() {
        Cart cart = prepareCart();
        Product product = prepareProduct();
        Product product1 = prepareProduct1();
        Author author = product.getAuthor();
        Genre genre = product.getGenre();
        product.setId(null);
        product1.setId(null);
        author.setId(null);
        genre.setId(null);
        cart.setId(null);
        cart.setProducts(new HashSet<>());
        product.getAuthor().setId(author.getId());
        product.getGenre().setId(genre.getId());
        product1.setAuthor(author);
        product1.setGenre(genre);
        authorRepository.save(author);
        genreRepository.save(genre);
        productRepository.save(product);
        productRepository.save(product1);
        cart.getProducts().add(product);
        cartRepository.save(cart);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> request = new HttpEntity<>(headers);
        ResponseEntity<CartDto> response = testRestTemplate.exchange(
                "/api/v1/carts/" + cart.getId() + "/products/" + product1.getId(),
                HttpMethod.PUT,
                request,
                CartDto.class);
        CartDto body = response.getBody();

        assertThat(body).isNotNull();
        assertThat(body.getProducts()).contains(productMapper.toSimpleDto(product), productMapper.toSimpleDto(product1));
    }
}