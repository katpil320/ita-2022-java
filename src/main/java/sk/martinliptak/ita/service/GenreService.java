package sk.martinliptak.ita.service;

import sk.martinliptak.ita.model.GenreDto;

import java.util.Collection;

/**
 * A service for manipulating genres
 * @see sk.martinliptak.ita.domain.Genre
 */
public interface GenreService {
    /**
     * Retrieves all genres from database
     * @return {@link java.util.List} of {@link GenreDto}
     */
    Collection<GenreDto> findAllGenres();
}
