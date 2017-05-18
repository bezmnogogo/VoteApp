package com.savchuk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by home on 18.05.17.
 */
public class HttpPassTestRequest {
    @JsonProperty
    List<PassTestQuestion> questions;

    public List<PassTestQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<PassTestQuestion> questions) {
        this.questions = questions;
    }
}
