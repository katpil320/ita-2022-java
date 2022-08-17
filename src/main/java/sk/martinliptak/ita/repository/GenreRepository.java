package sk.martinliptak.ita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.martinliptak.ita.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
