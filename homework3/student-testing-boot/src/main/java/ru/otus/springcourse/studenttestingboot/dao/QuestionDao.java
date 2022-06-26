package ru.otus.springcourse.studenttestingboot.dao;

import ru.otus.springcourse.studenttestingboot.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> getQuestions();

}
