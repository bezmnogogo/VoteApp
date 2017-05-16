package com.savchuk.dao.repository;

import com.savchuk.dao.entitties.CreatedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by home on 17.05.17.
 */
public interface ICreatedUserRepository extends JpaRepository<CreatedUser, Long> {

    @Query("select DISTINCT u from CreatedUser u where u.email = :email")
    CreatedUser findUserByEmail(@Param("email") String email);

}
