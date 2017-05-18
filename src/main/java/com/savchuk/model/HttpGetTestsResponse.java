package com.savchuk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.savchuk.dao.entitties.Test.Test;

import java.util.List;

/**
 * Created by home on 17.05.17.
 */
public class HttpGetTestsResponse extends HttpResponse {
    @JsonProperty
    List<Test> tests;

    public HttpGetTestsResponse(List<Test> tests) {
        this.tests = tests;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }
}
