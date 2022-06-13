package ru.otus.springcourse.service;

import org.springframework.stereotype.Service;
import ru.otus.springcourse.domain.Person;

@Service
public interface QuestionService {
    void startQuestionsFor(Person person);
}
