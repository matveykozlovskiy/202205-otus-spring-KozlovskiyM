package ru.otus.springcourse.service;

import org.springframework.stereotype.Service;
import ru.otus.springcourse.dao.PersonDao;

@Service
public class PersonServiceImpl implements PersonService {

    private final ConsoleWriterServiceImpl writer;
    private final PersonDao dao;

    public PersonServiceImpl(ConsoleWriterServiceImpl writer, PersonDao dao) {
        this.writer = writer;
        this.dao = dao;
    }

    @Override
    public void findOutName() {
        String firstName = writer.printAndRead("Enter your first name please: ");
        String lastName = writer.printAndRead("Enter your last name please: ");
        dao.save(firstName, lastName);
        writer.print("Hello " + firstName + " " + lastName);
    }
}
