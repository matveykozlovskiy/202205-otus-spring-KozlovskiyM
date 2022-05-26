package ru.otus.springcourse.dao;

import ru.otus.springcourse.domain.Question;
import ru.otus.springcourse.service.FileHandlerServiceImpl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {
    private final List<Question> questionList = new ArrayList<>();
    private String line = "";

    private final FileHandlerServiceImpl fileHandler;

    public QuestionDaoImpl(FileHandlerServiceImpl fileHandlerCsv) {
        this.fileHandler = fileHandlerCsv;
    }

    public List<Question> getQuestions() {
        try {
            BufferedReader bufferedReader = new BufferedReader(fileHandler.readFile());
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                questionList.add(new Question(Integer.valueOf(values[0]), values[1]));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return questionList;
    }

}
