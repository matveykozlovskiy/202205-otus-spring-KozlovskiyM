package ru.otus.springcourse.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.springcourse.dao.QuestionDao;
import ru.otus.springcourse.domain.Question;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao dao;

    @Value("${count.to.pass.exam}")
    private int countToPassExam;

    private final ConsoleWriterServiceImpl writer;

    public QuestionServiceImpl(QuestionDao dao, ConsoleWriterServiceImpl writer) {
        this.dao = dao;
        this.writer = writer;
    }

    private boolean IsAnswerCorrect(String personAnswer, String correctAnswer) {
        return personAnswer.trim().equalsIgnoreCase(correctAnswer.trim());
    }

    @Override
    public void startQuestions() {
        List<Question> questionList = dao.getQuestions();
        int counter = 0;
        for (Question question : questionList) {
            int questionId = question.getId();

            String personAnswer = writer.printAndRead(questionId + " question is: " + question.getText());

            dao.setPersonAnswer(questionId, personAnswer);

            if (IsAnswerCorrect(personAnswer, question.getCorrectAnswer())) {
                counter++;
            }
        }

        if (counter >= countToPassExam) {
            writer.print("Congratulations, you have successfully completed the questions");

        } else {
            writer.print("You made a mistake, try again");
        }
    }
}
