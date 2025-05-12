package com.example.diplamajava.ui.test.models;

import java.util.List;

public class Question {
    private String question;
    private List<String> departments;
    private boolean isAnswered = false;

    public Question(String question, List<String> departments) {
        this.question = question;
        this.departments = departments;
    }

    public boolean getAnswered() {
        return this.isAnswered;
    }

    public void setAnswered(boolean isAnswered) {
        this.isAnswered = isAnswered;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getDepartments() {
        return departments;
    }
}

