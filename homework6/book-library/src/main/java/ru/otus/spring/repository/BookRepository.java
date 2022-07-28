package ru.otus.spring.repository;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookRepository {

    void save(Book book);

    void deleteById(long id);

    void update(Book book);

    List<Book> getAll();

    Book getById(long id);
}
