package sk.martinliptak.ita.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.martinliptak.ita.model.GenreDto;
import sk.martinliptak.ita.service.impl.GenreServiceImpl;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/genres")
public class GenreController {
    private final GenreServiceImpl genreService;

    @GetMapping
    public Collection<GenreDto> findAllGenres() {
        return genreService.findAllGenres();
    }
}
