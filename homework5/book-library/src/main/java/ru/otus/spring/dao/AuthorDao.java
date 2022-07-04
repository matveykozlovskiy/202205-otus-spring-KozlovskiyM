package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;

import java.util.List;

public interface AuthorDao {
    void insert(Author author);

    List<Author> getAll();
}
