package com.savchuk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by home on 17.05.17.
 */
public class HttpTestQuickInfoResponse extends HttpResponse {
    @JsonProperty
    String title;

    public HttpTestQuickInfoResponse(String testName) {
        this.title = testName;
    }

    public String getTestName() {
        return title;
    }

    public void setTestName(String testName) {
        this.title = testName;
    }
}
