package com.savchuk.dao.entitties.Question;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.savchuk.dao.entitties.GenericEntity;
import com.savchuk.dao.entitties.User;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;
import java.util.List;

/**
 * Created by home on 17.04.17.
 */
@Entity
@Table(name = "question_category")
@EnableJpaRepositories
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class QuestionCategory extends GenericEntity {

    @JsonProperty
    @Column(name = "category", nullable = false)
    private String category;

    @JsonProperty
    @OneToMany(mappedBy = "questionCategory", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Question> questions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    public QuestionCategory() {
    }

    public QuestionCategory(String category, User user) {
        this.category = category;
        this.user = user;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
