package ru.otus.spring.Service;

public interface AuthorService {

    long addNew(String firstName, String middleName, String lastName);

    String showAll();
}
