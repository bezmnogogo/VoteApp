package com.savchuk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by home on 13.05.17.
 */
public class HttpErrorResponse extends HttpResponse{
    public HttpErrorResponse(int code, String message) {
        this.status = code;
        this.message = message;
    }

    @JsonProperty
    String message;
}
