package ru.otus.springcourse.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ConsoleWriterServiceImpl implements ConsoleWriterService {

    public void print(String line) {
        System.out.println(line);
    }

    public String read() {
        Scanner input = new Scanner(System.in);
        return input.nextLine().toUpperCase().trim();
    }

    public String printAndRead(String line) {
        print(line);
        return read();
    }
}
