package ru.otus.springcourse.exceptions;

import java.io.IOException;

public class QuestionIOException extends IOException {
    public QuestionIOException(String s){
        super(s);
    }
}
