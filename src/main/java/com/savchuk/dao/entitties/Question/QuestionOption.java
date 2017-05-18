package com.savchuk.dao.entitties.Question;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.savchuk.dao.entitties.GenericEntity;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;

/**
 * Created by home on 17.04.17.
 */
@Entity
@Table(name = "question_option")
@EnableJpaRepositories
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class QuestionOption extends GenericEntity {

    @JsonProperty
    @Column(name = "value", nullable = false)
    private String value;

    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    @JsonBackReference
    private Question question;

    @JsonProperty
    @Column(name = "isCorrect", nullable = false)
    private boolean isCorrect;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public String getOption() {
        return value;
    }

    public void setOption(String option) {
        this.value = option;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

}
