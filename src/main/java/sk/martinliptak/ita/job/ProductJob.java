package sk.martinliptak.ita.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sk.martinliptak.ita.service.impl.ProductServiceImpl;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductJob {
    private final ProductServiceImpl productService;

    @Scheduled(cron = "${app.jobs.updateStockWithCron}")
    private void updateProductStockFromWarehouse() {
        log.info("Updating stock from warehouse");
        productService.updateStockFromWarehouse();
    }
}
