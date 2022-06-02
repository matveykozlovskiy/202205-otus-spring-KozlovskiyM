package ru.otus.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.otus.springcourse.domain.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDaoImpl implements PersonDao {
    private List<Person> personList = new ArrayList<>();
    private int sequence;

    @Override
    public void save(String firstName, String lastName) {
        personList.add(new Person(firstName, lastName, ++sequence));
    }
}
