package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookDao {

    long insert(Book book);

    void deleteById(long id);

    void update(Book book);

    List<Book> getAll();

    Book getById(long id);
}
