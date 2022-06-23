package com.tuychiev.quiz;

import java.util.List;

public class Question {
    private String question;
    private String answer;
    private List<String> options;

    public String getQuestion() { return question; }
    public void setQuestion(String value) { this.question = value; }

    public String getAnswer() { return answer; }
    public void setAnswer(String value) { this.answer = value; }

    public List<String> getOptions() { return options; }
    public void setOptions(List<String> value) { this.options = value; }
}