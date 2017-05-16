package com.savchuk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by home on 13.05.17.
 */
public class HttpResponse {
    @JsonProperty
    int status = 200;

    public void setStatus(int status) {
        this.status = status;
    }
}
