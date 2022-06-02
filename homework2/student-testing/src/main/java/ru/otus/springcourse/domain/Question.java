package ru.otus.springcourse.domain;

public class Question {
    private int id;
    private String text;
    private String correctAnswer;

    private String personAnswer;

    public Question(int id, String text, String correctAnswer) {
        this.id = id;
        this.text = text;
        this.correctAnswer = correctAnswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPersonAnswer() {
        return personAnswer;
    }

    public void setPersonAnswer(String personAnswer) {
        this.personAnswer = personAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
