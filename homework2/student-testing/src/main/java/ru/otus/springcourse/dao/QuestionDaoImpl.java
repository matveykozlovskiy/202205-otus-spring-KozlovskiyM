package ru.otus.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.otus.springcourse.domain.Question;
import ru.otus.springcourse.exceptions.QuestionFileNotFoundException;
import ru.otus.springcourse.exceptions.QuestionIOException;
import ru.otus.springcourse.service.FileHandlerService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionDaoImpl implements QuestionDao {
    private final List<Question> questionList = new ArrayList<>();
    private final FileHandlerService fileHandler;

    public QuestionDaoImpl(FileHandlerService fileHandler) {
        this.fileHandler = fileHandler;
    }

    public List<Question> getQuestions() {
        String line = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(fileHandler.readFile());
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                questionList.add(new Question(Integer.valueOf(values[0]), values[1], values[2].toUpperCase().trim()));
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            throw new QuestionFileNotFoundException("Could not find file with questions");
        } catch (IOException e) {
            throw new QuestionIOException("Error reading questions");
        } finally {
          return questionList;
        }

    }

}
