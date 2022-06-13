package ru.otus.springcourse.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.springcourse.dao.QuestionDao;
import ru.otus.springcourse.domain.Person;
import ru.otus.springcourse.domain.Question;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao dao;
    private final int countToPassExam;
    private final IOService ioService;

    public QuestionServiceImpl(QuestionDao dao,
                               @Value("${count.to.pass.exam}") int countToPassExam,
                               IOService ioService) {
        this.dao = dao;
        this.countToPassExam = countToPassExam;
        this.ioService = ioService;
    }

    private boolean isAnswerCorrect(String personAnswer, String correctAnswer) {
        return personAnswer.trim().equalsIgnoreCase(correctAnswer.trim());
    }

    private void checkIsExamPassedFor(Person person, int countCorrectAnswer) {
        if (countCorrectAnswer >= countToPassExam) {
            ioService.print("Congratulations, " + person.toString() +
                    " you have successfully completed the questions");

        } else {
            ioService.print("You made a mistake, " + person.toString() +
                    " try again");
        }
    }

    private void provideInfoAboutExamFor(Person person) {
        ioService.print("Hello, " + person.toString() +
                " you need to answer questions. To pass the exam, you need to answer " + countToPassExam + " questions correctly");
    }

    @Override
    public void startQuestionsFor(Person person) {
        provideInfoAboutExamFor(person);

        List<Question> questionList = dao.getQuestions();
        int countCorrectAnswer = 0;
        for (Question question : questionList) {
            int questionId = question.getId();

            String personAnswer = ioService.printAndRead(questionId + " question is: " + question.getText());

            if (isAnswerCorrect(personAnswer, question.getCorrectAnswer())) {
                countCorrectAnswer++;
            }
        }
        checkIsExamPassedFor(person, countCorrectAnswer);
    }
}
