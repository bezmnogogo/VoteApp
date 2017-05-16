package com.savchuk.dao.repository;

import com.savchuk.dao.entitties.TestTimeLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by home on 17.04.17.
 */
@EnableJpaRepositories
@Component
public interface ITestTimeLimitRepository extends JpaRepository<TestTimeLimit, Long> {
}