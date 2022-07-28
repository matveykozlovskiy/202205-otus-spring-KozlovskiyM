package ru.otus.spring.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Books repository should ")
@DataJpaTest
@Import(BookRepositoryJpa.class)
class BookRepositoryJpaTest {

    @Autowired
    private BookRepositoryJpa bookRepositoryJpa;
    @Autowired
    private TestEntityManager em;

    private static final Long EXISTING_BOOK_ID = Long.valueOf(1);
    private static final String EXISTING_BOOK_NAME = "The Godfather";
    private static final Long EXISTING_AUTHOR_ID = Long.valueOf(1);
    private static final Long EXISTING_GENRE_ID = Long.valueOf(1);

    @DisplayName("Insert book in DB")
    @Test
    void shouldInsertBook() {
        var expectedBook = new Book(null, "Test book"
                , em.find(Author.class, EXISTING_AUTHOR_ID)
                , em.find(Genre.class, EXISTING_GENRE_ID));
        bookRepositoryJpa.save(expectedBook);

        List<Book> actualBookList = bookRepositoryJpa.getAll();
        assertThat(actualBookList).usingFieldByFieldElementComparator().containsAnyElementsOf(Collections.singleton(expectedBook));
    }

    @DisplayName("Return book by ID")
    @Test
    void shouldReturnExpectedBookById() {
        var expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_NAME
                , em.find(Author.class, EXISTING_AUTHOR_ID)
                , em.find(Genre.class, EXISTING_GENRE_ID));
        var actualBook = bookRepositoryJpa.getById(EXISTING_BOOK_ID);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("Delete book by ID")
    @Test
    void shouldDeleteById() {
        assertThat(em.find(Book.class, EXISTING_BOOK_ID)).isNotNull();
        bookRepositoryJpa.deleteById(EXISTING_BOOK_ID);
        assertThat(em.find(Book.class, EXISTING_BOOK_ID)).isNull();
    }

    @DisplayName("Return book list")
    @Test
    void shouldReturnExpectedBookList() {
        var expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_NAME, em.find(Author.class, EXISTING_AUTHOR_ID)
                , em.find(Genre.class, EXISTING_GENRE_ID));
        List<Book> actualBookList = bookRepositoryJpa.getAll();
        assertThat(actualBookList).usingFieldByFieldElementComparator().containsExactlyInAnyOrder(expectedBook);
    }
}