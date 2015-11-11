package com.harveynash.vn.training.service;

import java.util.Date;
import java.util.List;

import com.harveynash.vn.training.model.User;

public interface UserService {

    List<User> save(List<User> users);

    User save(User user);

    User findOne(Long id);

    User findByUsername(String username);

    List<User> findByFirstNameAndLastName(String firstName, String lastName);

    List<User> findByFirstNameOrLastName(String firstName, String lastName);

    User findUserByUsernameQueryDsl(String username);

    List<User> findByFirstNameAndLastNameQueryDsl(String firstName, String lastName);

    List<User> findByFirstNameOrLastNameQueryDsl(String firstName, String lastName);

    List<User> findByJoinDateBetween(Date from, Date to);

    List<User> findByActiveTrue();

    List<User> findByActiveFalse();

    User findByUsernameQuery(String username);

   void saySomething(String name);
}
