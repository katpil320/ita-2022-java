package sk.martinliptak.ita.repository;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import sk.martinliptak.ita.domain.Author;
import sk.martinliptak.ita.domain.Cart;
import sk.martinliptak.ita.domain.Genre;
import sk.martinliptak.ita.domain.Product;

import java.util.Set;

import static sk.martinliptak.ita.mother.CartMother.prepareCart;
import static sk.martinliptak.ita.mother.ProductMother.prepareProduct;

@DataJpaTest
class CartRepositoryIT implements WithAssertions {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void createAndRetrieve() {
        Cart cart = prepareCart();
        Product product = prepareProduct();
        Author author = product.getAuthor();
        Genre genre = product.getGenre();
        author.setId(null);
        genre.setId(null);
        product.setId(null);
        cart.setId(null);
        cart.setProducts(Set.of(product));

        testEntityManager.persistAndFlush(author);
        testEntityManager.persistAndFlush(genre);
        testEntityManager.persistAndFlush(product);
        testEntityManager.persistAndFlush(cart);
        testEntityManager.clear();

        Cart result = cartRepository.findById(cart.getId()).get();
        assertThat(result).usingRecursiveComparison().isEqualTo(cart);

        cartRepository.delete(cart);
        productRepository.delete(product);
        authorRepository.delete(author);
        genreRepository.delete(genre);
    }
}