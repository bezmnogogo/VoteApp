package com.savchuk.dao.repository.Test;

import com.savchuk.dao.entitties.Test.TestAccessUserJoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by home on 17.05.17.
 */
public interface ITestAccessUserJoinRepository extends JpaRepository<TestAccessUserJoin, Long> {
    @Query("select DISTINCT t from TestAccessUserJoin t where t.user.id = :userId and t.test.id = :testId")
    TestAccessUserJoin getTestAccessUserJoin(@Param("userId") long userId, @Param("testId") long testId);
}
