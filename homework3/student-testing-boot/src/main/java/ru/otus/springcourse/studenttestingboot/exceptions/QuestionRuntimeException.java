package ru.otus.springcourse.studenttestingboot.exceptions;

public class QuestionRuntimeException extends RuntimeException {
    public QuestionRuntimeException(String s) {
        super(s);
    }
}
