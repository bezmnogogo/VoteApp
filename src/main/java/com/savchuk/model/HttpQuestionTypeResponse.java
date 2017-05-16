package com.savchuk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.savchuk.dao.entitties.Question.QuestionType;

import java.util.List;

/**
 * Created by home on 14.05.17.
 */
public class HttpQuestionTypeResponse extends HttpResponse{
    @JsonProperty
    List<QuestionType> questionTypes;

    public HttpQuestionTypeResponse(List<QuestionType> questionTypes) {
        this.questionTypes = questionTypes;
    }

    public List<QuestionType> getQuestionTypes() {
        return questionTypes;
    }

    public void setQuestionTypes(List<QuestionType> questionTypes) {
        this.questionTypes = questionTypes;
    }
}
