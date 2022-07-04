package ru.otus.spring.domain;

import java.time.LocalDate;

public record Book(long id, String name, long authorId, long genreId, LocalDate releaseDate) {
}
