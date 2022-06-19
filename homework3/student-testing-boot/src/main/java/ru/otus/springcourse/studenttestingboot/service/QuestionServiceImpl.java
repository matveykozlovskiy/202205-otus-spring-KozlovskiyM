package ru.otus.springcourse.studenttestingboot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.springcourse.studenttestingboot.dao.QuestionDao;
import ru.otus.springcourse.studenttestingboot.domain.Person;
import ru.otus.springcourse.studenttestingboot.domain.Question;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao dao;
    private final int countToPassExam;
    private final IOService ioService;
    private final MessageService messageService;

    public QuestionServiceImpl(QuestionDao dao, @Value("${application.count-to-pass-exam}") int countToPassExam, IOService ioService, MessageService messageService) {
        this.dao = dao;
        this.countToPassExam = countToPassExam;
        this.ioService = ioService;
        this.messageService = messageService;
    }

    private boolean isAnswerCorrect(String personAnswer, String correctAnswer) {
        return personAnswer.trim().equalsIgnoreCase(correctAnswer.trim());
    }

    private void checkIsExamPassedFor(Person person, int countCorrectAnswer) {
        if (countCorrectAnswer >= countToPassExam) {
            ioService.print(person.toString() + " " + messageService.geLocalizedMessage("congratulations"));

        } else {
            ioService.print(person.toString() + " " + messageService.geLocalizedMessage("mistake"));
        }
    }

    private void provideInfoAboutExamFor(Person person) {
        ioService.print(messageService.geLocalizedMessage("greetings") + person.toString() + " " +
                        messageService.geLocalizedMessage("need.answer") + " " + countToPassExam + " " +
                        messageService.geLocalizedMessage("questions.correctly"));
    }

    @Override
    public void startQuestionsFor(Person person) {
        provideInfoAboutExamFor(person);

        List<Question> questionList = dao.getQuestions();
        int countCorrectAnswer = 0;
        for (Question question : questionList) {
            int questionId = question.getId();

            String personAnswer = ioService.printAndRead(questionId + " " +
                                                         messageService.geLocalizedMessage("question.is") + " " + question.getText());

            if (isAnswerCorrect(personAnswer, question.getCorrectAnswer())) {
                countCorrectAnswer++;
            }
        }
        checkIsExamPassedFor(person, countCorrectAnswer);
    }
}