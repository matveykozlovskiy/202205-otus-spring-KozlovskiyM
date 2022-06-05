package ru.otus.springcourse.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.springcourse.dao.QuestionDao;
import ru.otus.springcourse.domain.Question;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao dao;
    private final int countToPassExam;
    private final IOService ioService;
    private final PersonService personService;

    public QuestionServiceImpl(QuestionDao dao,
                               @Value("${count.to.pass.exam}") int countToPassExam,
                               IOService ioService, PersonService personService) {
        this.dao = dao;
        this.countToPassExam = countToPassExam;
        this.ioService = ioService;
        this.personService = personService;
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

            String personAnswer = ioService.printAndRead(questionId + " question is: " + question.getText());

            if (IsAnswerCorrect(personAnswer, question.getCorrectAnswer())) {
                counter++;
            }
        }

        if (counter >= countToPassExam) {
            ioService.print("Congratulations, " + personService.requestPersonInfo() +
                    " you have successfully completed the questions");

        } else {
            ioService.print("You made a mistake, " + personService.requestPersonInfo() +
                    " try again");
        }
    }
}
