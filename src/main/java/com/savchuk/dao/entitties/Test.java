package com.savchuk.dao.entitties;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by home on 17.04.17.
 */
@Entity
@Table(name = "test")
@EnableJpaRepositories
public class Test extends GenericEntity {

    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @JsonProperty
    @Column(name = "title", nullable = false)
    private String title;

    @JsonProperty
    @Column(name = "timeLimit")
    private int timeLimit;

    @JsonProperty
    @Column(name = "scale", nullable = false)
    private int scale;

    @JsonProperty
    @Column(name = "useQuestionWeight")
    private boolean useQuestionWeight;

    @JsonProperty
    @Column(name = "createTime", nullable = false)
    private Date createTime;

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public boolean isUseQuestionWeight() {
        return useQuestionWeight;
    }

    public void setUseQuestionWeight(boolean useQuestionWeight) {
        this.useQuestionWeight = useQuestionWeight;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    //    @JsonProperty
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "test")
//    List<TestQuestionJoin> questionJoins;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
