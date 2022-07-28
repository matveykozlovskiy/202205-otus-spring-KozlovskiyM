package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.repository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final StringConverter stringConverter;


    public AuthorServiceImpl(AuthorRepository authorRepository, StringConverter stringConverter) {
        this.authorRepository = authorRepository;
        this.stringConverter = stringConverter;
    }

    @Override
    @Transactional
    public void addNew(String firstName, String middleName, String lastName) {
        Author author = new Author(null, firstName, middleName, lastName);
        authorRepository.save(author);
    }

    @Override
    @Transactional(readOnly = true)
    public String showAll() {
        return stringConverter.convertListToString(authorRepository.getAll());
    }
}
