package com.oocl.coldplayfans.dto;

import java.util.List;

public class QuestionResponse {
    private Integer questionId;
    private List<Option> options;
    private String questionText;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public QuestionResponse(Integer questionId, List<Option> options, String questionText) {
        this.questionText = questionText;
        this.options = options;
        this.questionId = questionId;
    }
}
