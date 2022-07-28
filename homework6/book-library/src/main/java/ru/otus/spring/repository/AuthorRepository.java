package ru.otus.spring.repository;

import ru.otus.spring.domain.Author;

import java.util.List;

public interface AuthorRepository {
    void save(Author author);

    List<Author> getAll();

    Author getById(long id);
}
