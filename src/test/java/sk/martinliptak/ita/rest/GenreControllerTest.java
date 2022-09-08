package sk.martinliptak.ita.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import sk.martinliptak.ita.model.GenreDto;
import sk.martinliptak.ita.service.impl.GenreServiceImpl;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sk.martinliptak.ita.mother.GenreMother.prepareGenreDto;

@WebMvcTest(GenreController.class)
class GenreControllerTest extends AbstractControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GenreServiceImpl genreService;

    @Test
    void findAllGenres() throws Exception {
        GenreDto genreDto = prepareGenreDto();
        when(genreService.findAllGenres()).thenReturn(List.of(genreDto));
        mockMvc.perform(get("/api/v1/genres"))
                .andExpect(status().isOk())
                .andExpect(content().json(getJsonContent("/responses/genres/findAllGenres.json")));
    }
}