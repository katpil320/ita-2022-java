package sk.martinliptak.ita.service;

import sk.martinliptak.ita.model.GenreDto;

import java.util.Collection;

public interface GenreService {
    Collection<GenreDto> findAll();
}
