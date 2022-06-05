package ru.otus.springcourse.service;

import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    private final IOService ioService;
    private String firstName;
    private String lastName;

    public PersonServiceImpl(IOService ioService) {
        this.ioService = ioService;
    }

    public String requestPersonInfo() {
        return firstName + " " + lastName;
    }

    @Override
    public void findOutName() {
        firstName = ioService.printAndRead("Enter your first name please: ");
        lastName = ioService.printAndRead("Enter your last name please: ");
        ioService.print("Hello " + requestPersonInfo());
    }
}
