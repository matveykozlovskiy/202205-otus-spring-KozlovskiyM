package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.spring.domain.Book;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Books Dao should ")
@JdbcTest
@Import({BookDaoJdbc.class, AuthorDaoJdbc.class, GenreDaoJdbc.class})

class BookDaoJdbcTest {

    private static final int EXISTING_BOOK_ID = 1;
    private static final String EXISTING_BOOK_NAME = "The Godfather";
    private static final int EXISTING_AUTHOR_ID = 1;
    private static final int EXISTING_GENRE_ID = 1;
    private static final LocalDate EXISTING_RELEASE_DATE = LocalDate.parse("1969-03-10");
    @Autowired
    private BookDaoJdbc bookDaoJdbc;
    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;
    @Autowired
    private GenreDaoJdbc genreDaoJdbc;

    @DisplayName("Insert book in DB")
    @Test
    void shouldInsertBook() {
        var expectedBook = new Book(2, "Test book"
                , authorDaoJdbc.getById(EXISTING_AUTHOR_ID)
                , genreDaoJdbc.getById(EXISTING_GENRE_ID), LocalDate.parse("1867-01-01"));
        System.out.println(expectedBook.toString());
        bookDaoJdbc.insert(expectedBook);
        var actualBook = bookDaoJdbc.getById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("Return book by ID")
    @Test
    void shouldReturnExpectedBookById() {
        var expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_NAME
                , authorDaoJdbc.getById(EXISTING_AUTHOR_ID)
                , genreDaoJdbc.getById(EXISTING_GENRE_ID), EXISTING_RELEASE_DATE);
        var actualBook = bookDaoJdbc.getById(EXISTING_BOOK_ID);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("Delete book by ID")
    @Test
    void shouldDeleteById() {
        assertThatCode(() -> bookDaoJdbc.getById(EXISTING_BOOK_ID)).doesNotThrowAnyException();

        bookDaoJdbc.deleteById(EXISTING_BOOK_ID);

        assertThatThrownBy(() -> bookDaoJdbc.getById(EXISTING_BOOK_ID)).isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("Return book list")
    @Test
    void shouldReturnExpectedBookList() {
        var expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_NAME, authorDaoJdbc.getById(EXISTING_AUTHOR_ID)
                , genreDaoJdbc.getById(EXISTING_GENRE_ID), EXISTING_RELEASE_DATE);
        List<Book> actualBookList = bookDaoJdbc.getAll();
        assertThat(actualBookList).usingFieldByFieldElementComparator().containsExactlyInAnyOrder(expectedBook);
    }

}