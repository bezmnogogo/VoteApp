package com.savchuk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by home on 18.05.17.
 */
public class PassTestQuestion {
    @JsonProperty
    long id;

    @JsonProperty
    String question;

    @JsonProperty
    long questionTypeId;

    @JsonProperty
    List<PassTestQuestionOption> options;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public long getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(long questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    public List<PassTestQuestionOption> getOptions() {
        return options;
    }

    public void setOptions(List<PassTestQuestionOption> options) {
        this.options = options;
    }
}
