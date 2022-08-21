package sk.martinliptak.ita.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sk.martinliptak.ita.domain.Author;
import sk.martinliptak.ita.mapper.AuthorMapper;
import sk.martinliptak.ita.model.AuthorDto;
import sk.martinliptak.ita.repository.AuthorRepository;
import sk.martinliptak.ita.service.impl.AuthorServiceImpl;

import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static sk.martinliptak.ita.mother.AuthorMother.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {
    @InjectMocks
    private AuthorServiceImpl authorService;
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private AuthorMapper authorMapper;

    @Test
    void findAllAuthors() {
        Author author = prepareAuthor();
        Author author1 = prepareAuthor1();
        AuthorDto authorDto = prepareAuthorDto();
        AuthorDto authorDto1 = prepareAuthorDto1();

        Collection<AuthorDto> expectedResult = List.of(authorDto, authorDto1);

        when(authorRepository.findAll()).thenReturn(List.of(author, author1));
        when(authorMapper.toDto(author)).thenReturn(authorDto);
        when(authorMapper.toDto(author1)).thenReturn(authorDto1);

        Collection<AuthorDto> result = authorService.findAllAuthors();

        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);

        verify(authorRepository).findAll();
        verify(authorMapper).toDto(author);
        verify(authorMapper).toDto(author1);
    }
}