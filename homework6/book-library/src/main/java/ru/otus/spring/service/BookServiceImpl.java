package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.GenreRepository;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    private final StringConverter stringConverter;

    public BookServiceImpl(BookRepository bookRepository, GenreRepository genreRepository, AuthorRepository authorRepository, StringConverter stringConverter) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
        this.stringConverter = stringConverter;
    }

    @Override
    @Transactional(readOnly = true)
    public String showById(long id) {
        return stringConverter.convertToString(bookRepository.getById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public String showAll() {
        return stringConverter.convertListToString(bookRepository.getAll());
    }

    @Override
    @Transactional
    public void update(long id, String name, long authorId, long genreId) {
        Book book = new Book(id, name, authorRepository.getById(authorId), genreRepository.getById(genreId));
        bookRepository.update(book);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addNew(String name, long authorId, long genreId) {
        Book book = new Book(null, name, authorRepository.getById(authorId), genreRepository.getById(genreId));
        bookRepository.save(book);
    }
}
