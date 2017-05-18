package com.savchuk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.savchuk.dao.entitties.Question.Question;

/**
 * Created by home on 16.05.17.
 */
public class HttpAddTestRequest {
    @JsonProperty
    TestOptions testOptions;

    @JsonProperty
    String[] testPeople;

    @JsonProperty
    Question[] testQuestions;

    @JsonProperty
    QuestionWeight[] questionWeights;

    public QuestionWeight[] getQuestionWeights() {
        return questionWeights;
    }

    public void setQuestionWeights(QuestionWeight[] questionWeights) {
        this.questionWeights = questionWeights;
    }

    public TestOptions getTestOptions() {
        return testOptions;
    }

    public void setTestOptions(TestOptions testOptions) {
        this.testOptions = testOptions;
    }

    public String[] getTestPeople() {
        return testPeople;
    }

    public void setTestPeople(String[] testPeople) {
        this.testPeople = testPeople;
    }

    public Question[] getTestQuestions() {
        return testQuestions;
    }

    public void setTestQuestions(Question[] testQuestions) {
        this.testQuestions = testQuestions;
    }
}
