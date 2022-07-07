package ru.otus.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public AuthorDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public long insert(Author author) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("firstname", author.getFirstName());
        params.addValue("middlename", author.getMiddleName());
        params.addValue("lastname", author.getLastName());

        KeyHolder kh = new GeneratedKeyHolder();

        namedParameterJdbcOperations.update("insert into authors (firstname, middlename, lastname) values ( :firstname, :middlename, :lastname); "
                , params, kh);

        return kh.getKey().longValue();
    }

    @Override
    public List<Author> getAll() {
        return namedParameterJdbcOperations.query("select id, firstname, middlename, lastname from authors", new AuthorMapper());
    }

    @Override
    public Author getById(long id) {
        return namedParameterJdbcOperations.queryForObject("select id, firstname, middlename, lastname from authors where id = :id"
                , Map.of("id", id), new AuthorMapper());
    }


    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String firstName = rs.getString("firstname");
            String middleName = rs.getString("middlename");
            String lastName = rs.getString("lastname");
            return new Author(id, firstName, middleName, lastName);
        }
    }
}
