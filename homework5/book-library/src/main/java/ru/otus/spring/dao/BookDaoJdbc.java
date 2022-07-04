package ru.otus.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public void insert(Book book) {
        namedParameterJdbcOperations.update("insert into books (id, name, authorid, genreid, releasedate)" +
                        "values (:id, :name, :authorid, :genreid, :releasedate)"
                , Map.of("id", book.id(), "name", book.name(), "authorid", book.authorId(), "genreid", book.genreId(), "releasedate", book.releaseDate()));
    }

    @Override
    public void deleteById(long id) {
        namedParameterJdbcOperations.update("delete from books where id = :id", Map.of("id", id));
    }

    @Override
    public void update(Book book) {
        namedParameterJdbcOperations.update("update books set id = :id, name = :name, authorid = :authorid, genreid = :genreid, releasedate = :releasedate where id = :id"
                , Map.of("id", book.id(), "name", book.name(), "authorid", book.authorId(), "genreid", book.genreId(), "releasedate", book.releaseDate()));
    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.query("select id, name, authorid, genreid, releasedate from books", new BookMapper());
    }

    @Override
    public Book getById(long id) {
        return namedParameterJdbcOperations.queryForObject("select id, name, authorid, genreid, releasedate from books where id = :id"
                , Map.of("id", id), new BookMapper());
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            long authorId = rs.getLong("authorid");
            long genreId = rs.getLong("genreid");
            LocalDate releaseDate = rs.getDate("releasedate").toLocalDate();
            return new Book(id, name, authorId, genreId, releaseDate);
        }
    }
}
