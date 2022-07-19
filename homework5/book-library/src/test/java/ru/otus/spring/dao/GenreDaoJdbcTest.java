package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Genre;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Genres Dao should ")
@JdbcTest
@Import(GenreDaoJdbc.class)
class GenreDaoJdbcTest {

    private static final int EXISTING_GENRE_ID = 1;
    private static final String EXISTING_GENRE_NAME = "Novel";

    @Autowired
    private GenreDaoJdbc genreDaoJdbc;

    @DisplayName("Insert genre in DB")
    @Test
    void shouldInsertGenre() {
        var expectedGenre = new Genre(2, "Crime");
        genreDaoJdbc.insert(expectedGenre);
        List<Genre> actualGenreList = genreDaoJdbc.getAll();
        assertThat(actualGenreList).usingFieldByFieldElementComparator().containsAnyElementsOf(Collections.singleton(expectedGenre));
    }

    @DisplayName("Return genre list")
    @Test
    void shouldReturnExpectedGenreList() {
        var expectedGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);
        List<Genre> actualGenreList = genreDaoJdbc.getAll();
        assertThat(actualGenreList).usingFieldByFieldElementComparator().containsExactlyInAnyOrder(expectedGenre);
    }
}