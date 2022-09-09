package sk.martinliptak.ita.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sk.martinliptak.ita.service.impl.CartServiceImpl;

import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class CartJob {
    private final CartServiceImpl cartService;

    @Scheduled(cron = "${app.jobs.cleanUnusedCartsWithCron}")
    private void cleanUnusedCarts() {
        cartService.deleteUnusedCarts(java.time.Instant.now().minus(10, ChronoUnit.MINUTES));
    }
}
