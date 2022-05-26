package ru.otus.springcourse.service;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.springcourse.dao.QuestionDao;
import ru.otus.springcourse.domain.Question;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class QuestionServiceImplTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @DisplayName("startQuestions return some question")
    @Test
    void startQuestions() {
        List<Question> questionList = new ArrayList<>();
        questionList.add(0, new Question(1, "What is your name?"));

        QuestionDao questionDao = mock(QuestionDao.class);
        when(questionDao.getQuestions()).thenReturn(questionList);

        QuestionService questionService = new QuestionServiceImpl(questionDao);

        questionService.startQuestions();

        assertEquals(1 + " question is: " + "What is your name?", outputStreamCaptor.toString()
                .trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}