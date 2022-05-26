package ru.otus.springcourse.dao;

import ru.otus.springcourse.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> getQuestions();
}
