package ru.otus.spring.Service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Genre;

import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreDao dao;

    public GenreServiceImpl(GenreDao dao) {
        this.dao = dao;
    }

    public String showAll() {
        return dao.getAll().stream().map(Genre::toString).collect(Collectors.joining("\n"));
    }

    public long addNew(String name) {
        Genre genre = new Genre(name);
        return dao.insert(genre);
    }
}
