package com.example.diplamajava.ui.test.models;

import java.util.List;

public class Question {
    private String question;
    private List<String> departments;

    public Question(String question, List<String> departments) {
        this.question = question;
        this.departments = departments;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getDepartments() {
        return departments;
    }
}

