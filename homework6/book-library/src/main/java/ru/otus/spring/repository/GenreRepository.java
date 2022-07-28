package ru.otus.spring.repository;

import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreRepository {
    void save(Genre genre);
    List<Genre> getAll();

    Genre getById(long id);
}
