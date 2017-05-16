package com.savchuk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by home on 16.05.17.
 */
public class TestOptions {

    @JsonProperty
    int scale;

    @JsonProperty
    boolean useQuestionWeight;

    @JsonProperty
    int timeLimit;

    @JsonProperty
    boolean isTimeLimited;
}
