package com.savchuk.dao.repository.Test;

import com.savchuk.dao.entitties.Test.Test;
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
public interface ITestRepository extends JpaRepository<Test, Long> {
    @Query("select DISTINCT t from Test t where t.user.id = :userId")
    List<Test> findTestsByUserId(@Param("userId") long userId);
}
