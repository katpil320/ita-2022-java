package sk.martinliptak.ita.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class BaseServiceAspect {
    @Around("execution(* sk.martinliptak.ita.service.impl.*.*(..))")
    public Object aroundAllServiceMethods(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes attribs = RequestContextHolder.getRequestAttributes();
        if (attribs != null) {
            HttpServletRequest request = ((ServletRequestAttributes) attribs).getRequest();
            log.debug("Incoming {} request on {}", request.getMethod(), request.getRequestURL());
            log.debug("Executing {} - with args {}", pjp.getSignature(), pjp.getArgs());
        }
        Object result = pjp.proceed();
        if (result != null) {
            log.debug("Method {} finished and returned {}", pjp.getSignature().getName(), result);
        } else {
            log.debug("Method {} finished", pjp.getSignature().getName());
        }
        return result;
    }
}
