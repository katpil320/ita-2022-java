package sk.martinliptak.ita.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import sk.martinliptak.ita.domain.Author;
import sk.martinliptak.ita.model.AuthorDto;

import static sk.martinliptak.ita.mother.AuthorMother.prepareAuthor;
import static sk.martinliptak.ita.mother.AuthorMother.prepareAuthorDto;
import static org.assertj.core.api.Assertions.*;

class AuthorMapperTest {
    private final AuthorMapper authorMapper = Mappers.getMapper(AuthorMapper.class);

    @Test
    void toDto() {
        Author source = prepareAuthor();
        AuthorDto expectedResult = prepareAuthorDto();
        AuthorDto result = authorMapper.toDto(source);

        assertThat(result.getId()).isEqualTo(source.getId());
        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }
}