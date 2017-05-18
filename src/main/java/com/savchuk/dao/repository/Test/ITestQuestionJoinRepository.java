package com.savchuk.dao.repository.Test;

import com.savchuk.dao.entitties.Question.Question;
import com.savchuk.dao.entitties.Test.TestQuestionJoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by home on 16.05.17.
 */
@EnableJpaRepositories
@Component
public interface ITestQuestionJoinRepository extends JpaRepository<TestQuestionJoin, Long>{
    @Query("select DISTINCT t.question from TestQuestionJoin t where t.test.id = :testId")
    List<Question> getTestQuestions(@Param("testId") long testId);

    @Query("select DISTINCT t.questionWeight from TestQuestionJoin t where t.test.id = :testId and t.question.id = :questionId")
    int getTestQuestionWeight(@Param("testId") long testId, @Param("questionId") long questionId);
}
