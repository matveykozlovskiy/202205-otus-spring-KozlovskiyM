package ru.otus.springcourse.exceptions;

import java.io.FileNotFoundException;

public class QuestionFileNotFoundException extends FileNotFoundException {
    public QuestionFileNotFoundException(String s) {
        super(s);
    }
}
