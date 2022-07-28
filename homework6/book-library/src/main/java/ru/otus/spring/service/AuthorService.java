package ru.otus.spring.service;

public interface AuthorService {

    void addNew(String firstName, String middleName, String lastName);

    String showAll();
}
