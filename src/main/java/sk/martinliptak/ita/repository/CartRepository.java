package sk.martinliptak.ita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sk.martinliptak.ita.domain.Cart;

import java.time.Instant;
import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query(value = "SELECT c FROM Cart c WHERE c.modifiedAt < :modifiedTimestamp ")
    List<Cart> findAllByModifiedAtBefore(@Param("modifiedTimestamp") Instant modifiedTimestamp);
}
