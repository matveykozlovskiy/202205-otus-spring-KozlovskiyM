package ru.otus.springcourse.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionTest {

    @DisplayName("Constructor works correctly")
    @Test
    void getId() {
        Question question = new Question(8,"What is your name?");

        assertEquals(8,question.getId());
        assertEquals("What is your name?",question.getText());
    }
}