package ru.otus.springcourse.service;

import org.springframework.stereotype.Service;
import ru.otus.springcourse.domain.Person;

@Service
public class ApplicationRunner {

    private final PersonService personService;
    private final QuestionService questionService;

    public ApplicationRunner(PersonService personService, QuestionService questionService) {
        this.personService = personService;
        this.questionService = questionService;
    }

    public void run() {
        Person person = personService.requestPersonInfo();
        questionService.startQuestionsFor(person);
    }
}
