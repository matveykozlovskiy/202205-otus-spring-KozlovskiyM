package ru.otus.spring.Service;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.time.LocalDate;
import java.util.stream.Collectors;

@ShellComponent
public class LibraryServiceShell implements LibraryService {

    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final BookDao bookDao;

    public LibraryServiceShell(AuthorDao authorDao, GenreDao genreDao, BookDao bookDao) {
        this.authorDao = authorDao;
        this.genreDao = genreDao;
        this.bookDao = bookDao;
    }

    @ShellMethod(value = "Show all authors", key = {"show-all-authors", "authors"})
    @Override
    public String showAllAuthors() {
        return authorDao.getAll().stream().map(Author::toString).collect(Collectors.joining("\n"));
    }

    @ShellMethod(value = "Add new author", key = {"add-new-author", "new author"})
    @Override
    public void addNewAuthor(long id, String firstName, String middleName, String lastName) {
        Author author = new Author(id, firstName, middleName, lastName);
        authorDao.insert(author);
    }

    @ShellMethod(value = "Show all genres", key = {"show-all-genres", "genres"})
    @Override
    public String showAllGenres() {
        return genreDao.getAll().stream().map(Genre::toString).collect(Collectors.joining("\n"));
    }

    @ShellMethod(value = "Add new genre", key = {"add-new-genre", "new genre"})
    @Override
    public void addNewGenre(long id, String name) {
        Genre genre = new Genre(id, name);
        genreDao.insert(genre);
    }

    @ShellMethod(value = "Add new book", key = {"add-new-book", "new book"})
    @Override
    public void addNewBook(long id, String name, long authorId, long genreId, String releaseDate) {
        Book book = new Book(id, name, authorId, genreId, LocalDate.parse(releaseDate));
        bookDao.insert(book);
    }

    @ShellMethod(value = "Delete Book", key = {"delete-book-by-id", "delete book"})
    @Override
    public void deleteBookById(long id) {
        bookDao.deleteById(id);
    }

    @ShellMethod(value = "Update Book", key = {"update-book", "update book"})
    @Override
    public void updateBook(long id, String name, long authorId, long genreId, String releaseDate) {
        Book book = new Book(id, name, authorId, genreId, LocalDate.parse(releaseDate));
        bookDao.update(book);
    }

    @ShellMethod(value = "Show all books", key = {"show-all-books", "books"})
    @Override
    public String showAllBooks() {
        return bookDao.getAll().stream().map(Book::toString).collect(Collectors.joining("\n"));
    }

    @Override
    @ShellMethod(value = "Show book by id", key = {"show-book-by-id", "book"})
    public String showBookById(long id) {
        return bookDao.getById(id).toString();
    }
}
