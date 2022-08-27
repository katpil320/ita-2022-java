package sk.martinliptak.ita.repository;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import sk.martinliptak.ita.domain.Genre;


@SpringBootTest
@AutoConfigureTestDatabase
class GenreRepositoryIT implements WithAssertions {
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private RepositoryEntityCreationManager repositoryEntityCreationManager;

    @Test
    void CreateAndRetrieve() {
        Genre genre = repositoryEntityCreationManager.prepareGenre();

        Genre result = genreRepository.findById(genre.getId()).get();
        assertThat(result).usingRecursiveComparison().isEqualTo(genre);

        repositoryEntityCreationManager.clean();
    }

}