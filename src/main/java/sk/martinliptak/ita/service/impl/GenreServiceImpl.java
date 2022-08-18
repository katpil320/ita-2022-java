package sk.martinliptak.ita.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.martinliptak.ita.mapper.GenreMapper;
import sk.martinliptak.ita.model.GenreDto;
import sk.martinliptak.ita.repository.GenreRepository;
import sk.martinliptak.ita.service.GenreService;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    @Transactional(readOnly = true)
    public Collection<GenreDto> findAllGenres() {
        return genreRepository.findAll()
                .stream().map(genreMapper::toDto)
                .collect(Collectors.toList());
    }
}
