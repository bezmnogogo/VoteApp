package com.savchuk.dao.entitties;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by home on 17.04.17.
 */
@Entity
@Table(name = "created_user")
@EnableJpaRepositories
public class CreatedUser extends GenericEntity {
    @JsonProperty
    @Column(name = "email", nullable = false)
    private String email;

    @JsonProperty
    @Column(name = "corporation", nullable = false)
    private String corporation;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCorporation() {
        return corporation;
    }

    public void setCorporation(String corporation) {
        this.corporation = corporation;
    }
}
