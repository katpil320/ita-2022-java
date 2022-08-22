package sk.martinliptak.ita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.martinliptak.ita.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
