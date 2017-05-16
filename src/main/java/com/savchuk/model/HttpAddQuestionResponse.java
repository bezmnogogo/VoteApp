package com.savchuk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by home on 15.05.17.
 */
public class HttpAddQuestionResponse extends HttpResponse {
    @JsonProperty
    long id;

    public HttpAddQuestionResponse(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
