package com.savchuk.dao.entitties.Question;

import com.savchuk.dao.entitties.GenericEntity;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by home on 17.04.17.
 */
@Entity
@Table(name = "test_question_weight")
@EnableJpaRepositories
public class QuestionWeight extends GenericEntity {
}
