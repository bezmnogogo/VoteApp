package com.savchuk.dao.entitties.Test;

import com.savchuk.dao.entitties.CreatedUser;
import com.savchuk.dao.entitties.GenericEntity;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;

/**
 * Created by home on 17.05.17.
 */
@Entity
@Table(name = "test_access_created_user_join")
@EnableJpaRepositories
public class TestAccessCreatedUserJoin extends GenericEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_user_id", nullable = false)
    private CreatedUser createdUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

    public CreatedUser getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(CreatedUser createdUser) {
        this.createdUser = createdUser;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }
}
