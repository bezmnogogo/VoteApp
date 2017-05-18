package com.savchuk.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.savchuk.dao.entitties.Question.Question;

import java.util.List;

/**
 * Created by home on 17.05.17.
 */
public class HttpPassTestResponse extends HttpResponse {
    @JsonProperty
    @JsonManagedReference
    List<QuestionModel> testQuestions;

    public HttpPassTestResponse(List<QuestionModel> testQuestions) {
        this.testQuestions = testQuestions;
    }

    public HttpPassTestResponse() {
    }

    public List<QuestionModel> getTestQuestions() {
        return testQuestions;
    }

    public void setTestQuestions(List<QuestionModel> testQuestions) {
        this.testQuestions = testQuestions;
    }
}
