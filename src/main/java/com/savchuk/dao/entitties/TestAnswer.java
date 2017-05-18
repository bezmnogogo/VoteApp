package com.savchuk.dao.entitties;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.savchuk.dao.entitties.Question.QuestionOption;
import com.savchuk.dao.entitties.Test.CreatedUserTestResult;
import com.savchuk.dao.entitties.Test.UserTestResult;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;

/**
 * Created by home on 18.05.17.
 */
@Entity
@Table(name = "test_answer")
@EnableJpaRepositories
public class TestAnswer extends GenericEntity {
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_test_resutl_id")
    UserTestResult userTestResult;

    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_user_test_resutl_id")
    CreatedUserTestResult createdUserTestResult;

    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id", nullable = false)
    QuestionOption questionOption;

    @JsonProperty
    String answer;

    @JsonProperty
    boolean correct;

    public TestAnswer() {
    }

    public TestAnswer(UserTestResult userTestResult, CreatedUserTestResult createdUserTestResult, QuestionOption questionOption, String answer, boolean correct) {
        this.userTestResult = userTestResult;
        this.createdUserTestResult = createdUserTestResult;
        this.questionOption = questionOption;
        this.answer = answer;
        this.correct = correct;
    }

    public UserTestResult getUserTestResult() {
        return userTestResult;
    }

    public void setUserTestResult(UserTestResult userTestResult) {
        this.userTestResult = userTestResult;
    }

    public CreatedUserTestResult getCreatedUserTestResult() {
        return createdUserTestResult;
    }

    public void setCreatedUserTestResult(CreatedUserTestResult createdUserTestResult) {
        this.createdUserTestResult = createdUserTestResult;
    }

    public QuestionOption getQuestionOption() {
        return questionOption;
    }

    public void setQuestionOption(QuestionOption questionOption) {
        this.questionOption = questionOption;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
