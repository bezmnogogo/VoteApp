package com.savchuk.dao.repository;

import com.savchuk.dao.entitties.TestAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by home on 18.05.17.
 */
public interface ITestAnswerRepository extends JpaRepository<TestAnswer, Long> {
}
