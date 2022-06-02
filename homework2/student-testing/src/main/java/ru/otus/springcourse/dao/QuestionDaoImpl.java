package ru.otus.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.otus.springcourse.domain.Question;
import ru.otus.springcourse.service.FileHandlerServiceImpl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionDaoImpl implements QuestionDao {
    private final List<Question> questionList = new ArrayList<>();
    private final FileHandlerServiceImpl fileHandler;

    public QuestionDaoImpl(FileHandlerServiceImpl fileHandler) {
        this.fileHandler = fileHandler;
    }

    public void setPersonAnswer(int id, String personAnswer) {
        int questionId = id - 1;
        Question question = questionList.get(questionId);
        question.setPersonAnswer(personAnswer);
        questionList.set(questionId, question);

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
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
          return questionList;
        }

    }

}
