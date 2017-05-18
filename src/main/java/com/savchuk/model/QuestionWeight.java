package com.savchuk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by home on 18.05.17.
 */
public class QuestionWeight {
    @JsonProperty
    long questionId;

    @JsonProperty
    int weight;

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
