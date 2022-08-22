package sk.martinliptak.ita.mapper;

import org.mapstruct.Mapper;
import sk.martinliptak.ita.domain.Genre;
import sk.martinliptak.ita.model.GenreDto;

@Mapper
public interface GenreMapper {
    GenreDto toDto(Genre domain);
}
