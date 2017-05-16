package com.savchuk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.savchuk.dao.entitties.Question;

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
