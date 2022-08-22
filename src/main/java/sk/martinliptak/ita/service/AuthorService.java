package sk.martinliptak.ita.service;

import sk.martinliptak.ita.model.AuthorDto;

import java.util.Collection;

public interface AuthorService {
    Collection<AuthorDto> findAllAuthors();
}