package ru.otus.spring.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Author;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Authors repository should ")
@DataJpaTest
@Import(AuthorRepositoryJpa.class)
class AuthorRepositoryJpaTest {
    private static final int EXPECTED_AUTHOR_COUNT = 1;
    @Autowired
    private AuthorRepositoryJpa authorRepositoryJpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName("Insert author in DB")
    @Test
    void shouldInsertAuthor() {
        Author expectedAuthor = new Author(null, "FirstName", "SecondName", "LastName");
        authorRepositoryJpa.save(expectedAuthor);
        List<Author> actualAuthorList = authorRepositoryJpa.getAll();
        assertThat(actualAuthorList).usingFieldByFieldElementComparator().containsAnyElementsOf(Collections.singleton(expectedAuthor));
    }

    @DisplayName("Return Author list")
    @Test
    void shouldReturnExpectedAuthorList() {
        List<Author> actualAuthorList = authorRepositoryJpa.getAll();
        assertThat(actualAuthorList).hasSize(EXPECTED_AUTHOR_COUNT);
    }
}