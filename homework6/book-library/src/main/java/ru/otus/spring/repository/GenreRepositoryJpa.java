package ru.otus.spring.repository;

import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Genre;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class GenreRepositoryJpa implements GenreRepository {

    private final EntityManager em;

    public GenreRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Genre genre) {
        em.persist(genre);
    }

    @Override
    public List<Genre> getAll() {
        return em.createQuery("Select g from Genre g", Genre.class).getResultList();
    }

    @Override
    public Genre getById(long id) {
        return em.find(Genre.class, id);
    }
}
