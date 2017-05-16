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

    @JsonProperty
    String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public boolean isUseQuestionWeight() {
        return useQuestionWeight;
    }

    public void setUseQuestionWeight(boolean useQuestionWeight) {
        this.useQuestionWeight = useQuestionWeight;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public boolean isTimeLimited() {
        return isTimeLimited;
    }

    public void setTimeLimited(boolean timeLimited) {
        isTimeLimited = timeLimited;
    }
}
