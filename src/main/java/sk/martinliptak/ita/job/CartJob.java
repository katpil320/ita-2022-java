package sk.martinliptak.ita.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sk.martinliptak.ita.domain.Cart;
import sk.martinliptak.ita.repository.CartRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
@Slf4j
public class CartJob {
    private final CartRepository cartRepository;

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
    private void cleanUnusedCarts() {
        List<Cart> carts = cartRepository.findAll();
        Instant currentTimeInstant = Instant.now();
        AtomicInteger cartsCleaned = new AtomicInteger();
        carts.forEach(cart -> {
            if (cart.getModifiedAt().isBefore(currentTimeInstant.minus(10, ChronoUnit.MINUTES))) {
                cartRepository.delete(cart);
                cartsCleaned.getAndIncrement();
            }
        });
        log.info("{} carts cleaned", cartsCleaned);
    }
}
