package sk.martinliptak.ita.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import sk.martinliptak.ita.model.AuthorDto;
import sk.martinliptak.ita.service.impl.AuthorServiceImpl;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sk.martinliptak.ita.mother.AuthorMother.prepareAuthorDto;

@WebMvcTest(AuthorController.class)
class AuthorControllerTest extends AbstractControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AuthorServiceImpl authorService;

    @Test
    void findAllAuthors() throws Exception {
        AuthorDto authorDto = prepareAuthorDto();
        when(authorService.findAllAuthors()).thenReturn(List.of(authorDto));
        mockMvc.perform(get("/api/v1/authors"))
                .andExpect(status().isOk())
                .andExpect(content().json(getJsonContent("/responses/authors/findAllAuthors.json")));
    }
}