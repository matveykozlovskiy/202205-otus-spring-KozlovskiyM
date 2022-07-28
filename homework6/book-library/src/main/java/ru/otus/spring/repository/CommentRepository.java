package ru.otus.spring.repository;


import ru.otus.spring.domain.Comment;

import java.util.List;

public interface CommentRepository {
    void save(Comment comment);
    void deleteById(long id);

    void update(Comment comment);

    List<Comment> getByBookId(Long bookId);

    Comment getById(long id);
}
