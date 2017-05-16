package com.savchuk.dao.entitties;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    public boolean isSiCorrect() {
        return isCorrect;
    }

    public void setSiCorrect(boolean siCorrect) {
        this.isCorrect = siCorrect;
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
