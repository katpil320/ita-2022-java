package sk.martinliptak.ita.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("ita-warehouse")
public interface ItaWarehouseClient {
    @GetMapping("products/{id}/stock")
    Long getStock(@PathVariable("id") Long productId);
}
