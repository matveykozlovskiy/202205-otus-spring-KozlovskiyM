package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Author;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Authors Dao should ")
@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {

    private static final int EXISTING_AUTHOR_ID = 1;
    private static final String EXISTING_AUTHOR_FIRST_NAME = "Mario";
    private static final String EXISTING_AUTHOR_MIDDLE_NAME = "Gianluigi";
    private static final String EXISTING_AUTHOR_LAST_NAME = "Puzo";

    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;

    @DisplayName("Insert author in DB")
    @Test
    void shouldInsertAuthor() {
        var expectedAuthor = new Author(2, "Robert", "Cecil", "Martin");
        authorDaoJdbc.insert(expectedAuthor);
        List<Author> actualAuthorList = authorDaoJdbc.getAll();
        assertThat(actualAuthorList).usingFieldByFieldElementComparator().containsAnyElementsOf(Collections.singleton(expectedAuthor));
    }

    @DisplayName("Return Author list")
    @Test
    void shouldReturnExpectedAuthorList() {
        var expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_FIRST_NAME, EXISTING_AUTHOR_MIDDLE_NAME, EXISTING_AUTHOR_LAST_NAME);
        List<Author> actualAuthorList = authorDaoJdbc.getAll();
        assertThat(actualAuthorList).usingFieldByFieldElementComparator().containsExactlyInAnyOrder(expectedAuthor);
    }
}