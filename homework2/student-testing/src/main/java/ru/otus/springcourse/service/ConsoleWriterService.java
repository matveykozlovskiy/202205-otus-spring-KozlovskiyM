package ru.otus.springcourse.service;

public interface ConsoleWriterService {

    void print(String line);

    String read();

    String printAndRead(String line);
}
