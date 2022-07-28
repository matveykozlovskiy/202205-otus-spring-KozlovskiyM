package ru.otus.spring.controller;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.CommentService;
import ru.otus.spring.service.GenreService;

@ShellComponent
public class LibraryControllerShell {

    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookService bookService;
    private final CommentService commentService;

    public LibraryControllerShell(AuthorService authorService, GenreService genreService, BookService bookService, CommentService commentService) {
        this.authorService = authorService;
        this.genreService = genreService;
        this.bookService = bookService;
        this.commentService = commentService;
    }

    @ShellMethod(value = "Show all authors", key = {"show-all-authors", "authors"})
    public String showAllAuthors() {
        return authorService.showAll();
    }

    @ShellMethod(value = "Add new author", key = {"add-new-author", "new author"})
    public void addNewAuthor(String firstName, String middleName, String lastName) {
        authorService.addNew(firstName, middleName, lastName);
    }

    @ShellMethod(value = "Show all genres", key = {"show-all-genres", "genres"})
    public String showAllGenres() {
        return genreService.showAll();
    }

    @ShellMethod(value = "Add new genre", key = {"add-new-genre", "new genre"})
    public void addNewGenre(String name) {
        genreService.addNew(name);
    }

    @ShellMethod(value = "Add new book", key = {"add-new-book", "new book"})
    public void addNewBook(String name, long authorId, long genreId) {
        bookService.addNew(name, authorId, genreId);
    }

    @ShellMethod(value = "Delete Book", key = {"delete-book-by-id", "delete book"})
    public void deleteBookById(long id) {
        bookService.deleteById(id);
    }

    @ShellMethod(value = "Update Book", key = {"update-book", "update book"})
    public void updateBook(long id, String name, long authorId, long genreId) {
        bookService.update(id, name, authorId, genreId);
    }

    @ShellMethod(value = "Show all books", key = {"show-all-books", "books"})
    public String showAllBooks() {
        return bookService.showAll();
    }

    @ShellMethod(value = "Show book by id", key = {"show-book-by-id", "book"})
    public String showBookById(long id) {
        return bookService.showById(id);
    }

    @ShellMethod(value = "Show comment by book id", key = {"show-comment-by-book-id", "comment by bookid"})
    public String showCommentByBookId(long bookId){
        return commentService.showByBookId(bookId);
    }

    @ShellMethod(value = "Show comments by id", key = {"show-by-id", "comments"})
    public String showCommentsById(long id){
        return commentService.showById(id);
    }

    @ShellMethod(value = "Add new comment", key = {"add-new-comment", "new comment"})
    public void addNewComment(long bookId, String text){
        commentService.addNew(bookId, text);
    }

    @ShellMethod(value = "Update comment", key = {"update-comment", "update comment"})
    public void updateComment(long id, long bookId, String text){
        commentService.update(id, bookId, text);
    }

    @ShellMethod(value = "Delete comment by id",  key = {"delete-comment-by-id", "delete comment"})
    public void deleteCommentById(long id){
        commentService.deleteById(id);
    }
}
