package sk.martinliptak.ita.repository;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import sk.martinliptak.ita.domain.Author;


@SpringBootTest
@AutoConfigureTestDatabase
class AuthorRepositoryIT implements WithAssertions {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private RepositoryEntityCreationManager repositoryEntityCreationManager;

    @Test
    void CreateAndRetrieve() {
        Author author = repositoryEntityCreationManager.prepareAuthor();

        Author result = authorRepository.findById(author.getId()).get();
        assertThat(result).usingRecursiveComparison().isEqualTo(author);

        repositoryEntityCreationManager.clean();
    }
}