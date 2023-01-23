package com.example.quiz;

public class DirectQuestion  extends Question{
    private boolean response;
    public DirectQuestion(String question, boolean response) {
        super(question);
        this.response = response;

    }

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }
}
