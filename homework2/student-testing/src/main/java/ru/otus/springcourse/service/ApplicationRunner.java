package ru.otus.springcourse.service;

public class ApplicationRunner {

    private final PersonService personService;
    private final QuestionService questionService;

    public ApplicationRunner(PersonService personService, QuestionService questionService) {
        this.personService = personService;
        this.questionService = questionService;
    }

    public void run() {
        personService.findOutName();
        questionService.startQuestions();
    }
}
