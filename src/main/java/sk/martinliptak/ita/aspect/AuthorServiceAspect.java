package sk.martinliptak.ita.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AuthorServiceAspect {
    @Before("execution(* sk.martinliptak.ita.service.impl.AuthorServiceImpl.findAllAuthors(..))")
    public void logBeforeFindAll() {
        log.debug("Fetching all authors");
    }
}
