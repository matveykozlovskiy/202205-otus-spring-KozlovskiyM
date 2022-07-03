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
    private final IOMessageService ioMessageService;

    public QuestionServiceImpl(QuestionDao dao, @Value("${application.count-to-pass-exam}") int countToPassExam, IOMessageService ioMessageService) {
        this.dao = dao;
        this.countToPassExam = countToPassExam;
        this.ioMessageService = ioMessageService;
    }

    private boolean isAnswerCorrect(String personAnswer, String correctAnswer) {
        return personAnswer.trim().equalsIgnoreCase(correctAnswer.trim());
    }

    private void checkIsExamPassedFor(Person person, int countCorrectAnswer) {
        if (countCorrectAnswer >= countToPassExam) {
            ioMessageService.printLocalizedMessage("congratulations", new Object[]{person});

        } else {
            ioMessageService.printLocalizedMessage("mistake", new Object[]{person});
        }
    }

    private void provideInfoAboutExamFor(Person person) {
        ioMessageService.printLocalizedMessage("greetings", new Object[]{person, countToPassExam});
    }

    @Override
    public void startQuestionsFor(Person person) {
        provideInfoAboutExamFor(person);

        List<Question> questionList = dao.getQuestions();
        int countCorrectAnswer = 0;
        for (Question question : questionList) {
            int questionId = question.getId();

            String personAnswer = ioMessageService.printLocalizedMessageAndRead("question.is", new Object[]{questionId, question.getText()});

            if (isAnswerCorrect(personAnswer, question.getCorrectAnswer())) {
                countCorrectAnswer++;
            }
        }
        checkIsExamPassedFor(person, countCorrectAnswer);
    }
}