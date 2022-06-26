package ru.otus.springcourse.studenttestingboot.service;

import org.springframework.stereotype.Service;
import ru.otus.springcourse.studenttestingboot.domain.Person;

@Service
public class PersonServiceImpl implements PersonService {
    private final IOMessageService ioMessageService;

    public PersonServiceImpl(IOMessageService ioMessageService) {

        this.ioMessageService = ioMessageService;
    }
    public Person requestPersonInfo() {
        String firstName = ioMessageService.printLocalizedMessageAndRead("ask.first.name", null);
        String lastName = ioMessageService.printLocalizedMessageAndRead("ask.last.name", null);
        return new Person(firstName, lastName);
    }
}