package ru.otus.spring.Service;

public interface LibraryService {
    String showAllAuthors();

    void addNewAuthor(long id, String firstName, String middleName, String lastName);

    String showAllGenres();

    void addNewGenre(long id, String name);

    void addNewBook(long id, String name, long authorId, long genreId, String releaseDate);

    void deleteBookById(long id);

    void updateBook(long id, String name, long authorId, long genreId, String releaseDate);

    String showAllBooks();

    String showBookById(long id);
}
