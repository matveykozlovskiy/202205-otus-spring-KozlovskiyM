package ru.otus.springcourse.dao;

import ru.otus.springcourse.domain.Question;
import ru.otus.springcourse.exceptions.QuestionFileNotFoundException;
import ru.otus.springcourse.exceptions.QuestionIOException;

import java.util.List;

public interface QuestionDao {
    List<Question> getQuestions() throws QuestionIOException, QuestionFileNotFoundException;

}
