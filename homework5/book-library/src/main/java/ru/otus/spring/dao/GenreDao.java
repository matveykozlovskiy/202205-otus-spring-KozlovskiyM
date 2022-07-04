package ru.otus.spring.dao;

import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreDao {
    void insert(Genre genre);

    List<Genre> getAll();
}
