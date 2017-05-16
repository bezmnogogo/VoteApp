package com.savchuk.dao.repository.Question;

import com.savchuk.dao.entitties.Question.QuestionWeight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

/**
 * Created by home on 17.04.17.
 */
@EnableJpaRepositories
@Component
public interface IQuestionWeightRepository extends JpaRepository<QuestionWeight, Long> {
}
