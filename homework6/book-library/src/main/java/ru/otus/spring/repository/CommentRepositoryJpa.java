package ru.otus.spring.repository;

import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CommentRepositoryJpa implements CommentRepository {

    private final EntityManager em;

    public CommentRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Comment comment) {
        em.persist(comment);
    }

    @Override
    public void deleteById(long id) {
        em.remove(getById(id));
    }

    @Override
    public void update(Comment comment) {
        em.merge(comment);
    }

    @Override
    public List<Comment> getByBookId(Long bookId) {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c where c.book.id = :bookid", Comment.class);
        query.setParameter("bookid", bookId);
        return query.getResultList();
    }

    @Override
    public Comment getById(long id) {
        return em.find(Comment.class, id);
    }
}
