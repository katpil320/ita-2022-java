package sk.martinliptak.ita.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.martinliptak.ita.model.AuthorDto;
import sk.martinliptak.ita.service.AuthorService;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/authors")
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping
    public Collection<AuthorDto> findAllAuthors() {
        return authorService.findAllAuthors();
    }
}
