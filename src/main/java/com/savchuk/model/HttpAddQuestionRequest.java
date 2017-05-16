package com.savchuk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.savchuk.dao.entitties.QuestionCategory;
import com.savchuk.dao.entitties.QuestionOption;
import com.savchuk.dao.entitties.QuestionType;

import java.util.List;

/**
 * Created by home on 14.05.17.
 */
public class HttpAddQuestionRequest {
    @JsonProperty
    String question;

    @JsonProperty
    Long selectedTypeId;

    @JsonProperty
    long selectedCategoryId;

    @JsonProperty
    List<QuestionOption> selectedQuestionOptions;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Long getSelectedTypeId() {
        return selectedTypeId;
    }

    public void setSelectedTypeId(Long selectedTypeId) {
        this.selectedTypeId = selectedTypeId;
    }

    public long getSelectedCategoryId() {
        return selectedCategoryId;
    }

    public void setSelectedCategoryId(long selectedCategoryId) {
        this.selectedCategoryId = selectedCategoryId;
    }

    public List<QuestionOption> getSelectedQuestionOptions() {
        return selectedQuestionOptions;
    }

    public void setSelectedQuestionOptions(List<QuestionOption> selectedQuestionOptions) {
        this.selectedQuestionOptions = selectedQuestionOptions;
    }
}
