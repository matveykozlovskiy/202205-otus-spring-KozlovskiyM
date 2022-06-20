package ru.otus.springcourse.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class IOServiceImpl implements IOService {

    private final PrintStream output;
    private final Scanner input;

    public IOServiceImpl(@Value("#{ T(java.lang.System).out}") PrintStream outputStream,
                         @Value("#{ T(java.lang.System).in}") InputStream inputStream) {
        output = outputStream;
        input = new Scanner(inputStream);
    }

    public void print(String line) {
        output.println(line);
    }

    public String read() {
        return input.nextLine().toUpperCase().trim();
    }

    public String printAndRead(String line) {
        print(line);
        return read();
    }
}
