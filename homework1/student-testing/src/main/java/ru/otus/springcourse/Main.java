package ru.otus.springcourse;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.springcourse.service.QuestionServiceImpl;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        QuestionServiceImpl questionService = context.getBean(QuestionServiceImpl.class);
        questionService.startQuestions();
    }
}
