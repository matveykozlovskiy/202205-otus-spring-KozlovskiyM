package ru.otus.spring.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StringConverterImpl implements StringConverter{
    @Override
    public String convertListToString(List list) {
        return (String) list.stream().map(Object::toString).collect(Collectors.joining("\n"));
    }

    @Override
    public String convertToString(Object object) {
        return String.valueOf(object);
    }
}
