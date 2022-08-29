package sk.martinliptak.ita.repository;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import sk.martinliptak.ita.domain.*;

import java.util.Set;

import static sk.martinliptak.ita.mother.CartMother.prepareCart;
import static sk.martinliptak.ita.mother.OrderMother.prepareOrder;
import static sk.martinliptak.ita.mother.ProductMother.prepareProduct;

@DataJpaTest
class OrderRepositoryIT implements WithAssertions {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void createAndRetrieve() {
        Product product = prepareProduct();
        Author author = product.getAuthor();
        Genre genre = product.getGenre();
        Cart cart = prepareCart();
        Order order = prepareOrder();
        author.setId(null);
        genre.setId(null);
        product.setId(null);
        cart.setId(null);
        order.setId(null);
        cart.setProducts(Set.of(product));
        order.setProducts(cart.getProducts());

        testEntityManager.persistAndFlush(author);
        testEntityManager.persistAndFlush(genre);
        testEntityManager.persistAndFlush(product);
        testEntityManager.persistAndFlush(cart);
        testEntityManager.persistAndFlush(order);
        testEntityManager.clear();

        Order result = orderRepository.findById(order.getId()).get();
        assertThat(result).usingRecursiveComparison().isEqualTo(order);

        orderRepository.delete(order);
        cartRepository.delete(cart);
        productRepository.delete(product);
        authorRepository.delete(author);
        genreRepository.delete(genre);
    }
}