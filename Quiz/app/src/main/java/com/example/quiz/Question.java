package com.example.quiz;

public class Question {

    private String question;
    private boolean hint_showed;
    private boolean question_answered;

    public Question(String question) {
        this.question = question;
        this.hint_showed = false;
        this.question_answered = false;
    }

    public boolean isQuestion_answered() {
        return question_answered;
    }

    public void setQuestion_answered(boolean question_answered) {
        this.question_answered = question_answered;
    }

    public boolean isHint_showed() {
        return hint_showed;
    }

    public void setHint_showed(boolean hint_showed) {
        this.hint_showed = hint_showed;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


}
