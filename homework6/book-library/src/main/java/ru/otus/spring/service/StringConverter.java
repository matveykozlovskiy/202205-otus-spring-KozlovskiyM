package ru.otus.spring.service;

import java.util.List;

public interface StringConverter {
    String convertListToString(List list);

    String convertToString(Object object);
}
