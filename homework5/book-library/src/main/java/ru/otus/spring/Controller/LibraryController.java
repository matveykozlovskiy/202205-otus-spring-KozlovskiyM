package ru.otus.spring.Controller;

public interface LibraryController {
    String showAllAuthors();

    long addNewAuthor(String firstName, String middleName, String lastName);

    String showAllGenres();

    long addNewGenre(String name);

    long addNewBook(String name, long authorId, long genreId, String releaseDate);

    void deleteBookById(long id);

    void updateBook(long id, String name, long authorId, long genreId, String releaseDate);

    String showAllBooks();

    String showBookById(long id);
}
