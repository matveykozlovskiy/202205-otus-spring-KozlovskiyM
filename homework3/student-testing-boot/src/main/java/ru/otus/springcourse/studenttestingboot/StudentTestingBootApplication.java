package ru.otus.springcourse.studenttestingboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.springcourse.studenttestingboot.service.ApplicationRunner;

@SpringBootApplication
public class StudentTestingBootApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(StudentTestingBootApplication.class, args);

        ApplicationRunner applicationRunner = ctx.getBean(ApplicationRunner.class);
        applicationRunner.run();
    }

}
