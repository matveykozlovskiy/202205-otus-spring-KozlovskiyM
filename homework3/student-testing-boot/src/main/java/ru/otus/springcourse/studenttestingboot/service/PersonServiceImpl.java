package ru.otus.springcourse.studenttestingboot.service;

import org.springframework.stereotype.Service;
import ru.otus.springcourse.studenttestingboot.domain.Person;

@Service
public class PersonServiceImpl implements PersonService {
    private final IOService ioService;
    private final MessageService messageService;

    public PersonServiceImpl(IOService ioService, MessageService messageService) {
        this.ioService = ioService;
        this.messageService = messageService;
    }
    public Person requestPersonInfo() {
        String firstName = ioService.printAndRead(messageService.geLocalizedMessage("ask.first.name"));
        String lastName = ioService.printAndRead(messageService.geLocalizedMessage("ask.last.name"));
        return new Person(firstName, lastName);
    }
}