package sk.martinliptak.ita.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import sk.martinliptak.ita.model.CartDto;

@Aspect
@Component
@Slf4j
public class CartServiceAspect {
    @Before("execution(* sk.martinliptak.ita.service.impl.CartServiceImpl.findCart(..))" + "&& args(id)")
    public void beforeFindCartExecution(Long id) {
        log.debug("Fetching cart({})", id);
    }

    @Before("execution(* sk.martinliptak.ita.service.impl.CartServiceImpl.createCart(..))" + "&& args(productId)")
    public void beforeCreateCartExecution(Long productId) {
        log.info("Creating cart, adding product({})", productId);
    }
    @AfterReturning(value = "execution(* sk.martinliptak.ita.service.impl.CartServiceImpl.createCart(..))", returning = "result")
    public void afterCreateCartReturn(CartDto result) {
        log.debug("Cart created - {}", result);
    }

    @Before(value = "execution(* sk.martinliptak.ita.service.impl.CartServiceImpl.addToCart(..))" + "&& args(id, productId)", argNames = "id,productId")
    public void beforeAddToCartExecution(Long id, Long productId) {
        log.info("Adding product({}) to cart({})", productId, id);
    }
    @AfterReturning(value = "execution(* sk.martinliptak.ita.service.impl.CartServiceImpl.addToCart(..))", returning = "result")
    public void afterAddToCartReturn(CartDto result) {
        log.debug("Cart({}) updated - {}", result.getId(), result);
    }

}
