package com.savchuk.dao.entitties;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.savchuk.dao.entitties.Test.CreatedUserTestResult;
import com.savchuk.dao.entitties.Test.Test;
import com.savchuk.dao.entitties.Test.UserTestResult;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;

/**
 * Created by home on 17.04.17.
 */
@Entity
@Table(name = "notifications")
@EnableJpaRepositories
public class Notification extends GenericEntity {
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_test_resutl_id")
    UserTestResult userTestResult;

    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_user_test_resutl_id")
    CreatedUserTestResult createdUserTestResult;

    @JsonProperty
    @Column(name = "message")
    String message;

    public Notification(User user, UserTestResult userTestResult, CreatedUserTestResult createdUserTestResult, String message) {
        this.user = user;
        this.userTestResult = userTestResult;
        this.createdUserTestResult = createdUserTestResult;
        this.message = message;
    }

    public Notification() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
