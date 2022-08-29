package sk.martinliptak.ita.repository;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import sk.martinliptak.ita.domain.Author;
import sk.martinliptak.ita.domain.Genre;
import sk.martinliptak.ita.domain.Product;

import static sk.martinliptak.ita.mother.ProductMother.prepareProduct;

@DataJpaTest
class ProductRepositoryIT implements WithAssertions {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void createAndRetrieve() {
        Product product = prepareProduct();
        Author author = product.getAuthor();
        Genre genre = product.getGenre();
        product.setId(null);
        author.setId(null);
        genre.setId(null);

        testEntityManager.persistAndFlush(author);
        testEntityManager.persistAndFlush(genre);
        testEntityManager.persistAndFlush(product);
        testEntityManager.clear();
        Product result = productRepository.findById(product.getId()).get();
        assertThat(result).usingRecursiveComparison().isEqualTo(product);

        productRepository.delete(product);
        authorRepository.delete(author);
        genreRepository.delete(genre);
    }
}