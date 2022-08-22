package sk.martinliptak.ita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.martinliptak.ita.domain.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
