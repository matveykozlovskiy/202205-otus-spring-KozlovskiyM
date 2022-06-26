package ru.otus.springcourse.studenttestingboot.dao;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import ru.otus.springcourse.studenttestingboot.config.FileConfig;
import ru.otus.springcourse.studenttestingboot.domain.Question;
import ru.otus.springcourse.studenttestingboot.exceptions.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionDaoImpl implements QuestionDao {
    private static final String DELIMITER = ",";

    private final FileConfig fileConfig;

    public QuestionDaoImpl(FileConfig fileConfig) {
        this.fileConfig = fileConfig;
    }

    private BufferedReader readFile() throws IOException {
        Resource resource = new ClassPathResource(fileConfig.getFileName());
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
