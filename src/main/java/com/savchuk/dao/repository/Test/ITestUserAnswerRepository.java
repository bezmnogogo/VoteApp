package com.savchuk.dao.repository.Test;

import com.savchuk.dao.entitties.Test.TestUserAnswers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

/**
 * Created by home on 17.04.17.
 */
@EnableJpaRepositories
@Component
public interface ITestUserAnswerRepository extends JpaRepository<TestUserAnswers, Long> {
}