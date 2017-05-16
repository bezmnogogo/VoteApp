package com.savchuk.dao.entitties.Test;

import com.savchuk.dao.entitties.GenericEntity;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by home on 17.04.17.
 */
@Entity
@Table(name = "test_time_limit")
@EnableJpaRepositories
public class TestTimeLimit extends GenericEntity {
}
