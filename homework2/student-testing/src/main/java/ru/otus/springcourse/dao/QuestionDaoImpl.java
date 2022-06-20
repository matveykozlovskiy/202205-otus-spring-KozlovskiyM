package ru.otus.springcourse.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import ru.otus.springcourse.domain.Question;
import ru.otus.springcourse.exceptions.QuestionFileNotFoundException;
import ru.otus.springcourse.exceptions.QuestionIOException;
import ru.otus.springcourse.exceptions.QuestionRuntimeException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionDaoImpl implements QuestionDao {
    private final Resource resource;
    private static final String DELIMITER = ",";

    public QuestionDaoImpl(@Value("${file}") Resource resource) {
        this.resource = resource;
    }

    private BufferedReader readFile() throws IOException {
        InputStream inputStream = resource.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        return new BufferedReader(inputStreamReader);
    }

    public List<Question> getQuestions() {
        final List<Question> questionList = new ArrayList<>();
        String line = "";
        try (BufferedReader bufferedReader = readFile()) {
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(DELIMITER);
                questionList.add(new Question(Integer.valueOf(values[0]), values[1], values[2].toUpperCase().trim()));
            }
            return questionList;
        } catch (FileNotFoundException e) {
            throw new QuestionFileNotFoundException("Could not find file with questions");
        } catch (IOException e) {
            throw new QuestionIOException("Error reading questions");
        } catch (RuntimeException e) {
            throw new QuestionRuntimeException("Unexpected exception while getting list of questions");
        }
    }
}
