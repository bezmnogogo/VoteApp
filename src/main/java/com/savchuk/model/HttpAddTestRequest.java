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
}
