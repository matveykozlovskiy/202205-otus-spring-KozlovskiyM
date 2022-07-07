package ru.otus.spring.Service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.domain.Author;

import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao dao;

    public AuthorServiceImpl(AuthorDao dao) {
        this.dao = dao;
    }

    @Override
    public long addNew(String firstName, String middleName, String lastName) {
        Author author = new Author(firstName, middleName, lastName);
        return dao.insert(author);
    }

    @Override
    public String showAll() {
        return dao.getAll().stream().map(Author::toString).collect(Collectors.joining("\n"));
    }
}
