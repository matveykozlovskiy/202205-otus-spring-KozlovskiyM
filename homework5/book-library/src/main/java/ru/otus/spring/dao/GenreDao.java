package ru.otus.spring.dao;

import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreDao {
    long insert(Genre genre);
    List<Genre> getAll();

    Genre getById(long id);
}
