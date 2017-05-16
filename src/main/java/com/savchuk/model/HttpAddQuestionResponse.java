package com.savchuk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.savchuk.dao.entitties.Question.Question;

/**
 * Created by home on 15.05.17.
 */
public class HttpAddQuestionResponse extends HttpResponse {
    @JsonProperty
    Question question;

    public HttpAddQuestionResponse(Question question) {
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
