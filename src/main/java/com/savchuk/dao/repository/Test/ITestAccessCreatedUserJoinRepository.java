package com.savchuk.dao.repository.Test;

import com.savchuk.dao.entitties.Test.TestAccessCreatedUserJoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by home on 17.05.17.
 */
public interface ITestAccessCreatedUserJoinRepository extends JpaRepository<TestAccessCreatedUserJoin, Long> {
    @Query("select DISTINCT t from TestAccessCreatedUserJoin t where t.createdUser.id = :userId and t.test.id = :testId")
    TestAccessCreatedUserJoin getTestAccessCreatedUserJoin(@Param("userId") long userId, @Param("testId") long testId);
}
