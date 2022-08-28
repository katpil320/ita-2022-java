package sk.martinliptak.ita.repository;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sk.martinliptak.ita.domain.Cart;

@SpringBootTest
@AutoConfigureTestDatabase
class CartRepositoryIT implements WithAssertions {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private RepositoryEntityCreationManager repositoryEntityCreationManager;

    @Test
    @Transactional
    void CreateAndRetrieve() {
        Cart cart = repositoryEntityCreationManager.prepareCart();

        Cart result = cartRepository.findById(cart.getId()).get();

        assertThat(result).usingRecursiveComparison().isEqualTo(cart);

        repositoryEntityCreationManager.clean();
    }
}