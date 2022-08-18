package sk.martinliptak.ita.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class GenreServiceAspect {
    @Before("execution(* sk.martinliptak.ita.service.impl.GenreServiceImpl.findAllGenres())")
    public void beforeFindAllGenresExecution() {
        log.debug("Fetching all genres");
    }
}
