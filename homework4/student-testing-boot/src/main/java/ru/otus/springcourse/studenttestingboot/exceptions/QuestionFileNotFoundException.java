package ru.otus.springcourse.studenttestingboot.exceptions;

public class QuestionFileNotFoundException extends RuntimeException {
    public QuestionFileNotFoundException(String s) {
        super(s);
    }
}
