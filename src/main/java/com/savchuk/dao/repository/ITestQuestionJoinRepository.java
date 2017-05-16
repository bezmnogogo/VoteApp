package com.savchuk.dao.repository;

import com.savchuk.dao.entitties.TestQuestionJoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

/**
 * Created by home on 16.05.17.
 */
@EnableJpaRepositories
@Component
public interface ITestQuestionJoinRepository extends JpaRepository<TestQuestionJoin, Long>{
}
