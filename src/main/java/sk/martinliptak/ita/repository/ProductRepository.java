package sk.martinliptak.ita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.martinliptak.ita.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
