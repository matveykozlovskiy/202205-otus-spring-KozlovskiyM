package ru.otus.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

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
    public long insert(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValues(Map.of("name", book.getName(), "authorid", book.getAuthor().getId() , "genreid", book.getGenre().getId(), "releasedate", book.getReleaseDate()));

        KeyHolder kh = new GeneratedKeyHolder();

        namedParameterJdbcOperations.update("insert into books (name, authorid, genreid, releasedate)" +
                        "values (:name, :authorid, :genreid, :releasedate)"
                , params, kh);

        return kh.getKey().longValue();
    }

    @Override
    public void deleteById(long id) {
        namedParameterJdbcOperations.update("delete from books where id = :id", Map.of("id", id));
    }

    @Override
    public void update(Book book) {
        namedParameterJdbcOperations.update("update books set id = :id, name = :name, authorid = :authorid, genreid = :genreid, releasedate = :releasedate where id = :id"
                , Map.of("id", book.getId(), "name", book.getName(), "authorid", book.getAuthor().getId(), "genreid", book.getGenre().getId(), "releasedate", book.getReleaseDate()));
    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.query(
                "select b.id, b.name, b.authorid, b.genreid, b.releasedate," +
                        "a.firstname as authorfirstname, a.middlename as authormiddlename, a.lastname as authorlastname, " +
                        "g.name as genrename " +
                        "from books b " +
                        "join authors a on a.id = b.authorid " +
                        "join genres g on g.id= b.genreid ", new BookMapper());
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
            String authorFirstName = rs.getString("authorfirstname");
            String authorMiddleName = rs.getString("authormiddlename");
            String authorLastName = rs.getString("authorlastname");
            String genreName = rs.getString("genreName");
            return new Book(id, name
                    , new Author(authorId, authorFirstName, authorMiddleName, authorLastName)
                    , new Genre(genreId, genreName), releaseDate);
        }
    }
}
