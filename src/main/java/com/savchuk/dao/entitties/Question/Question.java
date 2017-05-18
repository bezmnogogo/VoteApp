package com.savchuk.dao.entitties.Question;

import com.fasterxml.jackson.annotation.*;
import com.savchuk.dao.entitties.GenericEntity;
import com.savchuk.dao.entitties.User;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by home on 17.04.17.
 */
@Entity
@Table(name = "question")
@EnableJpaRepositories
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Question extends GenericEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonProperty
    private User user;

    @JsonProperty
    @Column(name = "question", nullable = false)
    private String question;

    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_type_id", nullable = false)
    @JsonManagedReference
    private QuestionType questionType;

    @JsonProperty
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
    @JsonManagedReference
    private List<QuestionOption> options;


//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
//    List<TestQuestionJoin> questionJoins;

    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_category_id", nullable = false)
    @JsonManagedReference
    private QuestionCategory questionCategory;

    public QuestionCategory getQuestionCategory() {
        return questionCategory;
    }

    public void setQuestionCategory(QuestionCategory questionCategory) {
        this.questionCategory = questionCategory;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public List<QuestionOption> getOptions() {
        return options;
    }

    public void setOptions(List<QuestionOption> options) {
        this.options = options;
    }

    @JsonIgnore
    public List<QuestionOption> getCorrectOptions(){
        List<QuestionOption> correctOtions = new ArrayList<>();
        for(QuestionOption option : this.options){
            if(option.isCorrect())
                correctOtions.add(option);
        }
        return correctOtions;
    }
}
