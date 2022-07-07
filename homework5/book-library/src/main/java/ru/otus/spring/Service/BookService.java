package ru.otus.spring.Service;

public interface BookService {

    String showById(long id);

    String showAll();

    void update(long id, String name, long authorId, long genreId, String releaseDate);

    void deleteById(long id);

    long addNew(String name, long authorId, long genreId, String releaseDate);
}
