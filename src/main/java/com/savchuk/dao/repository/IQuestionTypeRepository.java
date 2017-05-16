package com.savchuk.dao.repository;

import com.savchuk.dao.entitties.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by home on 17.04.17.
 */
@EnableJpaRepositories
@Component
public interface IQuestionTypeRepository extends JpaRepository<QuestionType, Long> {

    @Query("select DISTINCT q from QuestionType q where q.type = :typeName")
    QuestionType getQuestionTypeByName(@Param("typeName") String typeName);
}
