package com.harveynash.vn.training.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.harveynash.vn.training.model.User;
import com.harveynash.vn.training.utils.MyException;

public interface UserRepository extends JpaRepository<User, Long>, QueryDslPredicateExecutor<User> {

    User findUserByUsername(String username);

    List<User> findByFirstNameAndLastName(String firstName, String lastName);

    List<User> findByFirstNameOrLastName(String firstName, String lastName);

    List<User> findByJoinDateBetweenOrderByLevelDesc(Date from, Date to);

    List<User> findByActiveTrue();

    List<User> findByActiveFalse();

    @Query("FROM User WHERE username = :username")
    User findByUsernameQuery(@Param("username") String username);

    /*void saySomething(String name) throws MyException;*/
}
