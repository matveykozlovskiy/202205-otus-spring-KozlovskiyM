package ru.otus.spring.Service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Book;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{

    private final BookDao bookDao;
    private final GenreDao genreDao;
    private final AuthorDao authorDao;

    public BookServiceImpl(BookDao bookDao, GenreDao genreDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.genreDao = genreDao;
        this.authorDao = authorDao;
    }

    @Override
    public String showById(long id) {
        return bookDao.getById(id).toString();
    }

    @Override
    public String showAll() {
        return bookDao.getAll().stream().map(Book::toString).collect(Collectors.joining("\n"));
    }

    @Override
    public void update(long id, String name, long authorId, long genreId, String releaseDate) {
        Book book = new Book(id, name, authorDao.getById(authorId), genreDao.getById(genreId), LocalDate.parse(releaseDate));
        bookDao.update(book);
    }

    @Override
    public void deleteById(long id) {
        bookDao.deleteById(id);

    }

    @Override
    public long addNew(String name, long authorId, long genreId, String releaseDate) {
        Book book = new Book(name, authorDao.getById(authorId), genreDao.getById(genreId), LocalDate.parse(releaseDate));
        return bookDao.insert(book);
    }
}
