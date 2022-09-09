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
import sk.martinliptak.ita.domain.*;
import sk.martinliptak.ita.mapper.OrderMapper;
import sk.martinliptak.ita.mapper.ProductMapper;
import sk.martinliptak.ita.model.OrderDto;
import sk.martinliptak.ita.repository.*;

import java.util.HashSet;

import static sk.martinliptak.ita.mother.CartMother.prepareCart;
import static sk.martinliptak.ita.mother.OrderMother.prepareOrder;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderControllerIT implements WithAssertions {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private OrderMapper orderMapper;

    private final String adminAuthToken = "Basic YWRtaW46cGFzc3dvcmQ=";

    @AfterEach
    void clean() {
        orderRepository.deleteAll();
        cartRepository.deleteAll();
        productRepository.deleteAll();
        authorRepository.deleteAll();
        genreRepository.deleteAll();
    }

    @Test
    void createOrder() {
        Cart cart = prepareCart();
        Product product = cart.getProducts().stream().toList().get(0);
        Author author = product.getAuthor();
        Genre genre = product.getGenre();
        cart.setId(null);
        cart.setProducts(new HashSet<>());
        product.setId(null);
        author.setId(null);
        genre.setId(null);
        product.getAuthor().setId(author.getId());
        product.getGenre().setId(genre.getId());
        authorRepository.save(author);
        genreRepository.save(genre);
        productRepository.save(product);
        cart.getProducts().add(product);
        cartRepository.save(cart);

        ResponseEntity<OrderDto> response = testRestTemplate.postForEntity("/api/v1/orders/cart/" + cart.getId(), new HttpHeaders(), OrderDto.class);
        OrderDto body = response.getBody();

        assertThat(body).isNotNull();
        assertThat(body.getProducts()).contains(productMapper.toSimpleDto(product)).hasSize(1);
        assertThat(body.getStatus()).isEqualTo(OrderStatus.NEW);
    }

    @Test
    void findOrders() {
        Order order = prepareOrder();
        Product product = order.getProducts().stream().toList().get(0);
        Author author = product.getAuthor();
        Genre genre = product.getGenre();
        product.getAuthor().setId(author.getId());
        product.getGenre().setId(genre.getId());
        order.setProducts(new HashSet<>());
        order.setId(null);
        product.setId(null);
        author.setId(null);
        genre.setId(null);
        authorRepository.save(author);
        genreRepository.save(genre);
        productRepository.save(product);
        order.getProducts().add(product);
        orderRepository.save(order);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, adminAuthToken);
        HttpEntity<Object> request = new HttpEntity<>(headers);

        ResponseEntity<OrderDto[]> response = testRestTemplate.exchange("/api/v1/orders", HttpMethod.GET, request, OrderDto[].class);
        OrderDto[] body = response.getBody();

        assertThat(body).isNotNull();
        assertThat(body).hasSize(1).contains(orderMapper.toDto(order));


    }

    @Test
    void updateOrderStatus() {
        Order order = prepareOrder();
        Product product = order.getProducts().stream().toList().get(0);
        Author author = product.getAuthor();
        Genre genre = product.getGenre();
        product.getAuthor().setId(author.getId());
        product.getGenre().setId(genre.getId());
        order.setProducts(new HashSet<>());
        order.setId(null);
        product.setId(null);
        author.setId(null);
        genre.setId(null);
        authorRepository.save(author);
        genreRepository.save(genre);
        productRepository.save(product);
        order.getProducts().add(product);
        orderRepository.save(order);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, adminAuthToken);
        HttpEntity<Object> request = new HttpEntity<>(headers);

        ResponseEntity<OrderDto> response = testRestTemplate.exchange(
                "/api/v1/orders/" + order.getId() + "/status/" + OrderStatus.COMPLETED,
                HttpMethod.PUT, request, OrderDto.class);
        OrderDto body = response.getBody();

        assertThat(body).isNotNull();
        assertThat(body.getStatus()).isEqualTo(OrderStatus.COMPLETED);


    }
}