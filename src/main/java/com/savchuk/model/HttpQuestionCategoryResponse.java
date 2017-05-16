package com.savchuk.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.savchuk.dao.entitties.QuestionCategory;

import java.util.List;
import java.util.Set;

/**
 * Created by home on 14.05.17.
 */
public class HttpQuestionCategoryResponse extends HttpResponse {
    @JsonProperty
    @JsonManagedReference
    List<QuestionCategory> categories;

    public HttpQuestionCategoryResponse(List<QuestionCategory> categories) {
        this.categories = categories;
    }

    public List<QuestionCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<QuestionCategory> categories) {
        this.categories = categories;
    }
}
