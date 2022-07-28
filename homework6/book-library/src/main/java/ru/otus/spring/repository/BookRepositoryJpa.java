package ru.otus.spring.repository;

import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class BookRepositoryJpa implements BookRepository {

    private final EntityManager em;

    public BookRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Book book) {
        em.persist(book);
    }

    @Override
    public void deleteById(long id) {
        em.remove(getById(id));
    }

    @Override
    public void update(Book book) {
        em.merge(book);
    }

    @Override
    public List<Book> getAll() {
        return em.createQuery("select b from Book b join fetch b.author join fetch b.genre", Book.class).getResultList();
    }

    @Override
    public Book getById(long id) {
        return em.find(Book.class, id);
    }
}
