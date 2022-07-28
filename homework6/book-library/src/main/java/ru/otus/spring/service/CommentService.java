package ru.otus.spring.service;

public interface CommentService {
    String showById(long id);

    String showByBookId(long bookId);

    void update(long id, long bookId, String text);

    void deleteById(long id);

    void addNew(long bookId, String text);
}
