package ru.otus.springcourse.studenttestingboot.service;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Service;
import ru.otus.springcourse.studenttestingboot.domain.Person;

@Service
@ShellComponent
public class ApplicationRunner {
    private final PersonService personService;
    private final QuestionService questionService;

    public ApplicationRunner(PersonService personService, QuestionService questionService) {
        this.personService = personService;
        this.questionService = questionService;
    }

    @ShellMethod(value = "run")
    public void run() {
        Person person = personService.requestPersonInfo();
        questionService.startQuestionsFor(person);
    }
}
