package sk.martinliptak.ita.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.martinliptak.ita.mapper.AuthorMapper;
import sk.martinliptak.ita.model.AuthorDto;
import sk.martinliptak.ita.repository.AuthorRepository;
import sk.martinliptak.ita.service.AuthorService;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    @Transactional(readOnly = true)
    public Collection<AuthorDto> findAllAuthors() {
        return authorRepository.findAll()
                .stream().map(authorMapper::toDto)
                .collect(Collectors.toList());
    }
}
