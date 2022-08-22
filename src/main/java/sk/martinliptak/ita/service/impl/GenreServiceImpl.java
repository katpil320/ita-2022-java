package sk.martinliptak.ita.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    @Transactional(readOnly = true)
    public Collection<GenreDto> findAllGenres() {
        log.info("Fetching all genres");
        return genreRepository.findAll()
                .stream().map(genreMapper::toDto)
                .collect(Collectors.toList());
    }
}
