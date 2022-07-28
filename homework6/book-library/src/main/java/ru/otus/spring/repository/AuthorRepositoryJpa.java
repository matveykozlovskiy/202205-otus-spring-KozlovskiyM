package ru.otus.spring.repository;

import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class AuthorRepositoryJpa implements AuthorRepository {

    private final EntityManager em;

    public AuthorRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Author author) {
        em.persist(author);
    }

    @Override
    public List<Author> getAll() {
        return em.createQuery("select a from Author a", Author.class).getResultList();
    }

    @Override
    public Author getById(long id) {
        return em.find(Author.class, id);
    }
}
