package com.example.quiz;

import java.util.List;

public class QuestionToMultipleResponse extends Question{
    private int response;

    List<String> choices;
    public QuestionToMultipleResponse(String question, Integer response, List<String> choices) {
        super(question);
        this.response = response;
        this.choices = choices;
    }

    public int getResponse() {
        return response;
    }

    public void setResponse(int response) {
        this.response = response;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }
}
