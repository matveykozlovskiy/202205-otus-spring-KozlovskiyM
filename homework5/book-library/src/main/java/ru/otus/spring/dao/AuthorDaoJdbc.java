package ru.otus.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
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
    public void insert(Author author) {
        namedParameterJdbcOperations.update("insert into authors(id, firstname, middlename, lastname) " +
                        "values (:id, :firstname, :middlename, :lastname)"
                , Map.of("id", author.id(), "firstname", author.firstName(), "middlename", author.middleName()
                        , "lastname", author.lastName()));
    }

    @Override
    public List<Author> getAll() {
        return namedParameterJdbcOperations.query("select id, firstname, middlename, lastname from authors", new AuthorMapper());
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
