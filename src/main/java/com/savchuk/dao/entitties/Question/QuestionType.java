package com.savchuk.dao.entitties.Question;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.savchuk.dao.entitties.GenericEntity;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;
import java.util.List;

/**
 * Created by home on 17.04.17.
 */
@Entity
@Table(name = "question_type")
@EnableJpaRepositories
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class QuestionType extends GenericEntity {

    @JsonProperty
    @Column(name = "type", nullable = false)
    private String type;

    @JsonProperty
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "questionType")
    @JsonBackReference
    private List<Question> questions;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
