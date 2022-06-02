package ru.otus.springcourse.dao;

import ru.otus.springcourse.domain.Person;

public interface PersonDao {
    void save(String firstName, String lastName);
}
