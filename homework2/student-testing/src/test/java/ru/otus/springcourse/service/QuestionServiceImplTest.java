package ru.otus.springcourse.service;

import java.io.ByteArrayOutputStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.springcourse.dao.QuestionDao;
import ru.otus.springcourse.domain.Question;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class QuestionServiceImplTest {
    @Mock
    private ConsoleWriterServiceImpl writer;

    @DisplayName("startQuestions return some question")
    @Test
    void startQuestions() {
        List<Question> questionList = new ArrayList<>();
        questionList.add(0, new Question(1, "1", "1"));

        QuestionDao questionDao = mock(QuestionDao.class);
        when(questionDao.getQuestions()).thenReturn(questionList);

        QuestionService questionService = new QuestionServiceImpl(questionDao, writer);
        given(writer.printAndRead(any())).willReturn(String.valueOf(Optional.of("1")));

        questionService.startQuestions();

        verify(writer, times(1)).printAndRead("1 question is: 1");
    }

}