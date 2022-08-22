package sk.martinliptak.ita.mapper;

import org.mapstruct.Mapper;
import sk.martinliptak.ita.domain.Author;
import sk.martinliptak.ita.model.AuthorDto;

@Mapper
public interface AuthorMapper {
    AuthorDto toDto(Author domain);
}
