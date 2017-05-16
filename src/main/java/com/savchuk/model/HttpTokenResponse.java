package com.savchuk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by home on 13.05.17.
 */
public class HttpTokenResponse extends HttpResponse{

    @JsonProperty
    String accessToken;

    public HttpTokenResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
