package ru.otus.springcourse.studenttestingboot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.springcourse.studenttestingboot.dao.QuestionDao;
import ru.otus.springcourse.studenttestingboot.domain.Person;
import ru.otus.springcourse.studenttestingboot.domain.Question;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class QuestionServiceImplTest {

    @MockBean
    private IOService ioService;
    @MockBean
    private QuestionDao dao;
    @MockBean
    private MessageService messageService;
    @Autowired
    private QuestionService questionService;

    @BeforeEach
    void setUp() {
        Question question = new Question(1, "text", "answer");
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(question);
        given(dao.getQuestions()).willReturn(questions);
    }

    @Test
    void startQuestionsForShouldReturnSomething() {
        Person person = new Person("Ivan", "Ivanov");
        given(ioService.printAndRead(any())).willReturn(String.valueOf(Optional.of(" ")));
        given(messageService.geLocalizedMessage(eq("question.is"), any())).willReturn("1 question is: text");

        questionService.startQuestionsFor(person);
        verify(ioService, times(1)).printAndRead("1 question is: text");

    }
}