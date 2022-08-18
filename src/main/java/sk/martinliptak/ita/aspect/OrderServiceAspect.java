package sk.martinliptak.ita.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import sk.martinliptak.ita.model.OrderDto;

@Aspect
@Component
@Slf4j
public class OrderServiceAspect {
    @Before("execution(* sk.martinliptak.ita.service.impl.OrderServiceImpl.createOrder(..))")
    public void beforeCreateOrderExecution() {
        log.info("Processing new order");
    }
    @AfterReturning(value = "execution(* sk.martinliptak.ita.service.impl.OrderServiceImpl.createOrder(..))" + "&& args(cartId)", returning = "result", argNames = "result,cartId")
    public void afterCreateOrderReturn(OrderDto result, Long cartId) {
        log.debug("New order processed - {}", result);
        log.debug("Cart({}) cleaned", cartId);
    }
}
