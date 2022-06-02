package ru.otus.springcourse.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class FileHandlerServiceImpl implements FileHandlerService {
    private final Resource resource;

    public FileHandlerServiceImpl(@Value("${file}") Resource resource) {
        this.resource = resource;
    }

    @Override
    public InputStreamReader readFile() {
        try {
            InputStream in = resource.getInputStream();
            return new InputStreamReader(in, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
