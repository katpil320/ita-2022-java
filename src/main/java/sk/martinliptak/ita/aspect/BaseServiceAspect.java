package sk.martinliptak.ita.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class BaseServiceAspect {
    private final HttpServletRequest request;

    @Around("execution(* sk.martinliptak.ita.service.impl.*.*(..))")
    public Object aroundAllServiceMethods(ProceedingJoinPoint pjp) throws Throwable {
        log.debug("Incoming {} request on {}", request.getMethod(), request.getRequestURL());
        log.debug("Executing {} - with args {}", pjp.getSignature(), pjp.getArgs());
        Object result = pjp.proceed();
        if (result != null) {
        log.debug("Method {} finished and returned {}", pjp.getSignature().getName(), result);
        } else {
            log.debug("Method {} finished", pjp.getSignature().getName());
        }
        return result;
    }
}
