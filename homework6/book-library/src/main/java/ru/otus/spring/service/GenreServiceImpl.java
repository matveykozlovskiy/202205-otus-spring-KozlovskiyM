package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.GenreRepository;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository dao;
    private final StringConverter stringConverter;

    public GenreServiceImpl(GenreRepository dao, StringConverter stringConverter) {
        this.dao = dao;
        this.stringConverter = stringConverter;
    }

    @Override
    @Transactional(readOnly = true)
    public String showAll() {
        return stringConverter.convertListToString(dao.getAll());
    }

    @Override
    @Transactional
    public void addNew(String name) {
        Genre genre = new Genre(null, name);
        dao.save(genre);
    }
}
