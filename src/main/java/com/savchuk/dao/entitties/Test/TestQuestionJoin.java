package com.savchuk.dao.entitties.Test;

import com.savchuk.dao.entitties.GenericEntity;
import com.savchuk.dao.entitties.Question.Question;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;

/**
 * Created by home on 15.05.17.
 */
@Entity
@Table(name = "test_question_join")
@EnableJpaRepositories
public class TestQuestionJoin extends GenericEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }
}
