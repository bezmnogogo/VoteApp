package com.savchuk.dao.entitties.Test;

import com.savchuk.dao.entitties.CreatedUser;
import com.savchuk.dao.entitties.GenericEntity;
import com.savchuk.dao.entitties.User;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;

/**
 * Created by home on 17.05.17.
 */
@Entity
@Table(name = "test_access_user_join")
@EnableJpaRepositories
public class TestAccessUserJoin extends GenericEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

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
}
