package sk.martinliptak.ita.repository;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import sk.martinliptak.ita.domain.Product;

@SpringBootTest
@AutoConfigureTestDatabase
class ProductRepositoryIT implements WithAssertions {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RepositoryEntityCreationManager repositoryEntityCreationManager;

    @Test
    void CreateAndRetrieve() {
        Product product = repositoryEntityCreationManager.prepareProduct();

        Product result = productRepository.findById(product.getId()).get();

        assertThat(result).usingRecursiveComparison().isEqualTo(product);

        repositoryEntityCreationManager.clean();
    }
}