package com.savchuk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by home on 18.05.17.
 */
public class PassTestQuestionOption {
    @JsonProperty
    long id;

    @JsonProperty
    String value;

    @JsonProperty
    String answer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
