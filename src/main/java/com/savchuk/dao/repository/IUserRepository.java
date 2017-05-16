package com.savchuk.dao.repository;

import com.savchuk.dao.entitties.User;
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
public interface IUserRepository extends JpaRepository<User, Long> {

    @Query("SELECT DISTINCT u FROM User u")
    List<User> findAll();

    @Query("select case when count(u)>0 then true else false end from User u where u.email = :email")
    boolean checkIfMailExists(@Param("email") String email);

    @Query("select DISTINCT u from User u where u.email = :email and u.password = :password")
    User findUserByEmailAndPassword(@Param("email") String email, @Param("password") String passwrod);
}
