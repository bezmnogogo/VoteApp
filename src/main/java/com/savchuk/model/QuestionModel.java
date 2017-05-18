package com.savchuk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.savchuk.dao.entitties.Question.Question;
import com.savchuk.dao.entitties.Question.QuestionOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by home on 17.05.17.
 */
public class QuestionModel {
    @JsonProperty
    long id;

    @JsonProperty
    String question;

    @JsonProperty
    long questionTypeId;

    @JsonProperty
    List<QuestionOptionModel> options;

    public QuestionModel() {
    }

    @Autowired
    public QuestionModel(Question question) {
        this.id = question.getId();
        this.question = question.getQuestion();
        this.questionTypeId = question.getQuestionType().getId();

        this.options = new ArrayList<>();
        for(QuestionOption option : question.getOptions()){
            if(questionTypeId != 3) {
                this.options.add(new QuestionOptionModel(option.getId(), option.getOption()));
            } else {
                this.options.add(new QuestionOptionModel(option.getId(), "напишите ответ:"));
            }
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public long getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(long questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    public List<QuestionOptionModel> getOptions() {
        return options;
    }

    public void setOptions(List<QuestionOptionModel> options) {
        this.options = options;
    }
}
