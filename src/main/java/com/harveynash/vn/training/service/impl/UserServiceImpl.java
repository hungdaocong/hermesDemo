package com.harveynash.vn.training.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.harveynash.vn.training.dao.UserRepository;
import com.harveynash.vn.training.model.User;
import com.harveynash.vn.training.service.UserService;
import com.harveynash.vn.training.utils.MyException;
import com.harveynash.vn.training.utils.UserPredicates;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    public List<User> save(List<User> users) {
        List<User> list = repository.save(users);
        repository.flush();
        return list;
    }

    public User save(User user) {
        return repository.saveAndFlush(user);
    }

    public User findOne(Long id) {
        return repository.findOne(id);
    }

    public User findByUsername(String username) {
		// I would like to do something here
        return repository.findUserByUsername(username);
    }

    public List<User> findByFirstNameAndLastName(String firstName, String lastName) {
		// I would like to do something here
        return repository.findByFirstNameAndLastName(firstName, lastName);
    }

    public List<User> findByFirstNameOrLastName(String firstName, String lastName) {
		// I would like to do something here
        return repository.findByFirstNameOrLastName(firstName, lastName);
    }

    public User findUserByUsernameQueryDsl(String username) {
        return repository.findOne(UserPredicates.usernameEquals(username));
    }

    public List<User> findByFirstNameAndLastNameQueryDsl(String firstName, String lastName) {
        return Lists.newArrayList(repository.findAll(UserPredicates.firstNameAndLastNameEquals(firstName, lastName)));
    }

    public List<User> findByFirstNameOrLastNameQueryDsl(String firstName, String lastName) {
        return Lists.newArrayList(repository.findAll(UserPredicates.firstNameOrLastNameEquals(firstName, lastName)));
    }

    public List<User> findByJoinDateBetween(Date from, Date to) {
        return repository.findByJoinDateBetweenOrderByLevelDesc(from, to);
    }

    public List<User> findByActiveTrue() {
        return repository.findByActiveTrue();
    }

    public List<User> findByActiveFalse() {
        return repository.findByActiveFalse();
    }

    public User findByUsernameQuery(String username) {
        return repository.findByUsernameQuery(username);
    }

    public void saySomething(String name) {
        try {
            repository.saySomething(name);
            System.out.println("Wow! Congrats " + name.toUpperCase() + ", your method has been successfully called.");
        } catch (MyException e) {
            System.err.println("Opps! Something wrong " + name.toUpperCase() + ", please help to check this again.");
        }
    }

}
