package ru.otus.springcourse.service;

import org.springframework.core.io.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileHandlerServiceImpl implements FileHandlerService {
    private final Resource resource;

    public FileHandlerServiceImpl(Resource resource) {
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
