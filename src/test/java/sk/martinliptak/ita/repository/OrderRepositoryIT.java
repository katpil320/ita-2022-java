package sk.martinliptak.ita.repository;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sk.martinliptak.ita.domain.Order;


@SpringBootTest
@AutoConfigureTestDatabase
class OrderRepositoryIT implements WithAssertions {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RepositoryEntityCreationManager repositoryEntityCreationManager;

    @Test
    @Transactional
    void CreateAndRetrieve() {
        Order order = repositoryEntityCreationManager.prepareOrder();

        Order result = orderRepository.findById(order.getId()).get();

        assertThat(result).usingRecursiveComparison().isEqualTo(order);

        repositoryEntityCreationManager.clean();
    }

}