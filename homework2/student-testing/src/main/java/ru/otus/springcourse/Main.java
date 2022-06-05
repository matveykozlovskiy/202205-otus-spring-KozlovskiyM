package ru.otus.springcourse;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.springcourse.service.ApplicationRunner;
import ru.otus.springcourse.service.PersonService;
import ru.otus.springcourse.service.QuestionService;

@PropertySource("classpath:application.properties")
@Configuration
@ComponentScan
public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        QuestionService questionService = context.getBean(QuestionService.class);
        PersonService personService = context.getBean(PersonService.class);

        new ApplicationRunner(personService, questionService).run();
    }
}
