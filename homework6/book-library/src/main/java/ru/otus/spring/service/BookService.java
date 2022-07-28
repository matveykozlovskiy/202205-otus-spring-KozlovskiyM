package ru.otus.spring.service;

public interface BookService {

    String showById(long id);

    String showAll();

    void update(long id, String name, long authorId, long genreId);

    void deleteById(long id);

    void addNew(String name, long authorId, long genreId);
}
