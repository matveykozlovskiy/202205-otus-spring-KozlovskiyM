package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Book{
    private long id;
    private final String name;
    private final Author author;
    private final Genre genre;
    private final LocalDate releaseDate;
}
