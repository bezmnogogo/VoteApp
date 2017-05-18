package com.savchuk.dao.repository.Test;

import com.savchuk.dao.entitties.Test.UserTestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by home on 17.05.17.
 */
public interface IUserTestResultRepository extends JpaRepository<UserTestResult, Long> {
    @Query("select DISTINCT t from UserTestResult t where t.user.id = :userId and t.test.id = :testId and t.endTime is null")
    List<UserTestResult> findStartedTest(@Param("userId") long userId, @Param("testId") long testId);
}
