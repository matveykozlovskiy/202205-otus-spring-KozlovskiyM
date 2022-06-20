package ru.otus.springcourse.service;

import org.springframework.stereotype.Service;
import ru.otus.springcourse.domain.Person;

@Service
public class PersonServiceImpl implements PersonService {
    private final IOService ioService;

    public PersonServiceImpl(IOService ioService) {
        this.ioService = ioService;
    }

    public Person requestPersonInfo() {
        String firstName = ioService.printAndRead("Enter your first name please: ");
        String lastName = ioService.printAndRead("Enter your last name please: ");
        return new Person(firstName, lastName);
    }
}
