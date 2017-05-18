package com.savchuk.dao.repository.Test;

import com.savchuk.dao.entitties.CreatedUser;
import com.savchuk.dao.entitties.Test.CreatedUserTestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by home on 17.05.17.
 */
public interface ICreatedUserTestResultRepository extends JpaRepository<CreatedUserTestResult, Long> {

    @Query("select DISTINCT t from CreatedUserTestResult t where t.createdUser.id = :userId and t.test.id = :testId and t.endTime is null")
    List<CreatedUserTestResult> findStartedTest(@Param("userId") long userId, @Param("testId") long testId);
}
