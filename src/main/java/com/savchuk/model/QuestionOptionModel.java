package com.savchuk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by home on 17.05.17.
 */
public class QuestionOptionModel {
    @JsonProperty
    long id;

    @JsonProperty
    String value;

    @Autowired
    public QuestionOptionModel(long id, String value) {
        this.id = id;
        this.value = value;
    }

    public QuestionOptionModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
