package com.savchuk.dao.entitties.Test;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.savchuk.dao.entitties.GenericEntity;
import com.savchuk.dao.entitties.User;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by home on 17.05.17.
 */
@Entity
@Table(name = "user_test_result")
@EnableJpaRepositories
public class UserTestResult extends GenericEntity {
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id", nullable = false)
    Test test;

    @JsonProperty
    @Column(name = "startTime", nullable = false)
    private Date startTime;

    @JsonProperty
    @Column(name = "endTime")
    private Date endTime;

    @JsonProperty
    @Column(name = "valuation")
    Integer valuation;

    public int getValuation() {
        return valuation;
    }

    public void setValuation(int valuation) {
        this.valuation = valuation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
