package ru.otus.spring.Controller;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.Service.AuthorService;
import ru.otus.spring.Service.BookService;
import ru.otus.spring.Service.GenreService;

@ShellComponent
public class LibraryControllerShell implements LibraryController {

    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookService bookService;

    public LibraryControllerShell(AuthorService authorService, GenreService genreService, BookService bookService) {
        this.authorService = authorService;
        this.genreService = genreService;
        this.bookService = bookService;
    }

    @ShellMethod(value = "Show all authors", key = {"show-all-authors", "authors"})
    @Override
    public String showAllAuthors() {
        return authorService.showAll();
    }

    @ShellMethod(value = "Add new author", key = {"add-new-author", "new author"})
    @Override
    public long addNewAuthor(String firstName, String middleName, String lastName) {
        return authorService.addNew(firstName, middleName, lastName);
    }

    @ShellMethod(value = "Show all genres", key = {"show-all-genres", "genres"})
    @Override
    public String showAllGenres() {
        return genreService.showAll();
    }

    @ShellMethod(value = "Add new genre", key = {"add-new-genre", "new genre"})
    @Override
    public long addNewGenre(String name) {
        return genreService.addNew(name);
    }

    @ShellMethod(value = "Add new book", key = {"add-new-book", "new book"})
    @Override
    public long addNewBook(String name, long authorId, long genreId, String releaseDate) {
        return bookService.addNew(name, authorId, genreId, releaseDate);
    }

    @ShellMethod(value = "Delete Book", key = {"delete-book-by-id", "delete book"})
    @Override
    public void deleteBookById(long id) {
        bookService.deleteById(id);
    }

    @ShellMethod(value = "Update Book", key = {"update-book", "update book"})
    @Override
    public void updateBook(long id, String name, long authorId, long genreId, String releaseDate) {
        bookService.update(id, name, authorId, genreId, releaseDate);
    }

    @ShellMethod(value = "Show all books", key = {"show-all-books", "books"})
    @Override
    public String showAllBooks() {
        return bookService.showAll();
    }

    @Override
    @ShellMethod(value = "Show book by id", key = {"show-book-by-id", "book"})
    public String showBookById(long id) {
        return bookService.showById(id);
    }
}
