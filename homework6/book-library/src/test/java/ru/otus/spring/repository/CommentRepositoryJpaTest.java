package ru.otus.spring.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Comments repository should ")
@DataJpaTest
@Import(CommentRepositoryJpa.class)
class CommentRepositoryJpaTest {

    public static final Long EXISTING_BOOK_ID = Long.valueOf(1);
    public static final Long EXISTING_COMMENT_ID = Long.valueOf(1);
    public static final String EXISTING_TEXT = "Text";
    @Autowired
    private CommentRepositoryJpa commentRepositoryJpa;
    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Save comment")
    void shouldSaveComment() {
        var book = em.find(Book.class, EXISTING_BOOK_ID);
        var expectedComment = new Comment(null, book, "Some text");
        commentRepositoryJpa.save(expectedComment);

        List<Comment> actualCommentList = commentRepositoryJpa.getByBookId(EXISTING_BOOK_ID);
        assertThat(actualCommentList).usingFieldByFieldElementComparator().containsAnyElementsOf(Collections.singleton(expectedComment));
    }

    @Test
    @DisplayName("Return comment by Id")
    void shouldReturnExpectedCommentById() {
        var expectedComment = new Comment(EXISTING_COMMENT_ID
                , em.find(Book.class, EXISTING_BOOK_ID)
                , EXISTING_TEXT);
        var actualComment = commentRepositoryJpa.getById(EXISTING_COMMENT_ID);
        assertThat(actualComment).usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @Test
    @DisplayName("Delete comment by Id")
    void shouldDeleteById() {
        assertThat(em.find(Comment.class, EXISTING_COMMENT_ID)).isNotNull();
        commentRepositoryJpa.deleteById(EXISTING_COMMENT_ID);
        assertThat(em.find(Comment.class, EXISTING_COMMENT_ID)).isNull();
    }

    @Test
    @DisplayName("Return comments by book Id")
    void shouldReturnExpectedCommentsByBookId() {
        var expectedComment = new Comment(EXISTING_COMMENT_ID
                , em.find(Book.class, EXISTING_BOOK_ID)
                , EXISTING_TEXT);
        List<Comment> actualCommentList = commentRepositoryJpa.getByBookId(EXISTING_BOOK_ID);
        assertThat(actualCommentList).usingFieldByFieldElementComparator().containsExactlyInAnyOrder(expectedComment);

    }
}