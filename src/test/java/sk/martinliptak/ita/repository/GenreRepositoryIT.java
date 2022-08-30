package sk.martinliptak.ita.repository;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import sk.martinliptak.ita.domain.Genre;
import sk.martinliptak.ita.mother.GenreMother;

@DataJpaTest
public class GenreRepositoryIT implements WithAssertions {
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void createAndRetrieve() {
        Genre genre = GenreMother.prepareGenre();
        genre.setId(null);
        testEntityManager.persistAndFlush(genre);
        testEntityManager.detach(genre);
        Genre result = genreRepository.findById(genre.getId()).get();
        assertThat(result).usingRecursiveComparison().isEqualTo(genre);

        genreRepository.deleteById(genre.getId());
    }
}
