package ru.otus.springcourse.exceptions;

public class QuestionFileNotFoundException extends RuntimeException {
    public QuestionFileNotFoundException(String s) {
        super(s);
    }
}
