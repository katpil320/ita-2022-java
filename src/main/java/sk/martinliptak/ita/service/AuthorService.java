package sk.martinliptak.ita.service;

import sk.martinliptak.ita.model.AuthorDto;

import java.util.Collection;

/**
 * A service for manipulating Authors
 * @see sk.martinliptak.ita.domain.Author
 */
public interface AuthorService {
    /**
     * Retrieves all authors from database
     * @return {@link java.util.List} of {@link AuthorDto}
     */
    Collection<AuthorDto> findAllAuthors();
}