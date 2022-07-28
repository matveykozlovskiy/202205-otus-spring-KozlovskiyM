package ru.otus.spring.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Genre;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Genres repository should ")
@DataJpaTest
@Import(GenreRepositoryJpa.class)
class GenreRepositoryJdbcTest {

    private static final Long EXISTING_GENRE_ID = Long.valueOf(1);
    private static final String EXISTING_GENRE_NAME = "Novel";
    @Autowired
    private GenreRepositoryJpa genreRepositoryJpa;

    @DisplayName("Insert genre in DB")
    @Test
    void shouldInsertGenre() {
        var expectedGenre = new Genre(null, "Crime");
        genreRepositoryJpa.save(expectedGenre);
        List<Genre> actualGenreList = genreRepositoryJpa.getAll();
        assertThat(actualGenreList).usingFieldByFieldElementComparator().containsAnyElementsOf(Collections.singleton(expectedGenre));
    }

    @DisplayName("Return genre list")
    @Test
    void shouldReturnExpectedGenreList() {
        var expectedGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);
        List<Genre> actualGenreList = genreRepositoryJpa.getAll();
        assertThat(actualGenreList).usingFieldByFieldElementComparator().containsExactlyInAnyOrder(expectedGenre);
    }
}