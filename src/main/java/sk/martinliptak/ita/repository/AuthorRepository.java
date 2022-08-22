package sk.martinliptak.ita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.martinliptak.ita.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
