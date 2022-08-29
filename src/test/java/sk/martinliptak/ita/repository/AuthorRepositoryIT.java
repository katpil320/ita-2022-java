package sk.martinliptak.ita.repository;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import sk.martinliptak.ita.domain.Author;
import sk.martinliptak.ita.mother.AuthorMother;


@DataJpaTest
class AuthorRepositoryIT implements WithAssertions {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void createAndRetrieve() {
        Author author = AuthorMother.prepareAuthor();
        author.setId(null);
        testEntityManager.persistAndFlush(author);
        testEntityManager.detach(author);
        Author result = authorRepository.findById(author.getId()).get();
        assertThat(result).usingRecursiveComparison().isEqualTo(author);

        authorRepository.deleteById(author.getId());
    }
}