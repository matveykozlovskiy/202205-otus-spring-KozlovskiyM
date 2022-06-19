package ru.otus.springcourse.studenttestingboot.service;

import org.springframework.stereotype.Service;
import ru.otus.springcourse.studenttestingboot.domain.Person;

@Service
public class ApplicationRunner {
    private final PersonService personService;
    private final QuestionService questionService;
    private final MessageService messageService;

    public ApplicationRunner(PersonService personService, QuestionService questionService, MessageService messageService) {
        this.personService = personService;
        this.questionService = questionService;
        this.messageService = messageService;
    }

    public void run() {
        messageService.requestLanguage();
        Person person = personService.requestPersonInfo();
        questionService.startQuestionsFor(person);
    }
}
