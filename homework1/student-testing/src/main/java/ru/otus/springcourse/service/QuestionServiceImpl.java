package ru.otus.springcourse.service;

import ru.otus.springcourse.dao.QuestionDao;
import ru.otus.springcourse.domain.Question;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao dao;

    public QuestionServiceImpl(QuestionDao dao) {
        this.dao = dao;
    }

    @Override
    public void startQuestions() {
        List<Question> questionList = dao.getQuestions();
        for (Question question : questionList) {
            System.out.println(question.getId() + " question is: " + question.getText());
        }
    }
}
