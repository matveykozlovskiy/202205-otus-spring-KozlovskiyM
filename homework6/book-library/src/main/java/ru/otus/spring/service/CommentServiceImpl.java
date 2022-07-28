package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;
    private final StringConverter stringConverter;

    public CommentServiceImpl(CommentRepository commentRepository, BookRepository bookRepository, StringConverter stringConverter) {
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
        this.stringConverter = stringConverter;
    }

    @Override
    @Transactional(readOnly = true)
    public String showById(long id) {
        return stringConverter.convertToString(commentRepository.getById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public String showByBookId(long bookId) {
        return stringConverter.convertToString(commentRepository.getByBookId(bookId));
    }

    @Override
    @Transactional
    public void update(long id, long bookId, String text) {
        Comment comment = new Comment(id, bookRepository.getById(bookId), text);
        commentRepository.update(comment);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addNew(long bookId, String text) {
        Comment comment = new Comment(null, bookRepository.getById(bookId), text);
        commentRepository.save(comment);
    }
}
