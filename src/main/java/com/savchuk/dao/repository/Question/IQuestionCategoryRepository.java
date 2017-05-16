package com.savchuk.dao.repository.Question;

import com.savchuk.dao.entitties.Question.QuestionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by home on 17.04.17.
 */
@EnableJpaRepositories
@Component
public interface IQuestionCategoryRepository extends JpaRepository<QuestionCategory, Long> {

    @Query("select DISTINCT q from QuestionCategory q where q.user.id = :userId")
    List<QuestionCategory> getUserCategories(@Param("userId") long userId);

}
