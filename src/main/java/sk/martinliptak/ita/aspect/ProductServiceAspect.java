package sk.martinliptak.ita.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import sk.martinliptak.ita.model.ProductDto;
import sk.martinliptak.ita.model.ProductRequestDto;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class ProductServiceAspect {
    private final String incomingPayloadLogPattern = "Incoming {} request on {}, payload={}";
    private final HttpServletRequest request;

    // findProduct()
    @Before("execution(* sk.martinliptak.ita.service.impl.ProductServiceImpl.findProduct(..))" + "&& args(id)")
    public void beforeFindProductExecution(Long id) {
        log.debug("Fetching product ({})", id);
    }

    // findAllProducts()
    @Before("execution(* sk.martinliptak.ita.service.impl.ProductServiceImpl.findAllProducts())")
    public void beforeFindAllProductsExecution() {
        log.debug("Fetching all products");
    }

    // createProduct()
    @Before("execution(* sk.martinliptak.ita.service.impl.ProductServiceImpl.createProduct(..))" + "&& args(requestDto)")
    public void beforeCreateProductExecution(ProductRequestDto requestDto) {
        log.debug(incomingPayloadLogPattern, request.getMethod(), request.getRequestURI(), requestDto);
        log.info("Creating product");
    }

    @AfterReturning(value = "execution(* sk.martinliptak.ita.service.impl.ProductServiceImpl.createProduct(..))", returning = "result")
    public void afterCreateProductReturn(ProductDto result) {
        log.debug("Created new product - {}", result);
    }

    // updateProduct()
    @Before(value = "execution(* sk.martinliptak.ita.service.impl.ProductServiceImpl.updateProduct(..))" + "&& args(requestDto, id)", argNames = "requestDto,id")
    public void beforeUpdateProductExecution(ProductRequestDto requestDto, Long id) {
        log.debug(incomingPayloadLogPattern, request.getMethod(), request.getRequestURI(), requestDto);
        log.info("Updating product ({})", id);
    }

    @AfterReturning(value = "execution(* sk.martinliptak.ita.service.impl.ProductServiceImpl.updateProduct(..))", returning = "result")
    public void afterUpdateProductReturn(ProductDto result) {
        log.debug("Product updated - {}", result);
    }

    // deleteProduct()
    @Before("execution(* sk.martinliptak.ita.service.impl.ProductServiceImpl.deleteProduct(..))" + "&& args(id)")
    public void beforeDeleteProductExecution(Long id) {
        log.info("Deleting product ({})", id);
    }

    @AfterReturning("execution(* sk.martinliptak.ita.service.impl.ProductServiceImpl.deleteProduct(..))")
    public void afterDeleteProductReturn() {
        log.debug("Product deleted");
    }
}
