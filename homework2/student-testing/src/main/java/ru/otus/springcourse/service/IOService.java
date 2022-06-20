package ru.otus.springcourse.service;

public interface IOService {

    void print(String line);

    String read();

    String printAndRead(String line);
}
