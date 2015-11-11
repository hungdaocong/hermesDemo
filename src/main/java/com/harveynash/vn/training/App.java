package com.harveynash.vn.training;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.CollectionUtils;

import com.harveynash.vn.training.model.User;
import com.harveynash.vn.training.service.UserService;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:persistence-context.xml");
        UserService userService = context.getBean(UserService.class);
        createUsers(userService);
        testFindUserById(userService, 1L);
        testFindUserByUsername(userService, "test2");
        testFindByFirstNameAndLastName(userService, "Duncan", "Howard");
        testFindByFirstNameOrLastName(userService, "Truong", "Mai");
        testFindUserByUsernameQueryDsl(userService, "test3");
        testFindByFirstNameAndLastNameQueryDsl(userService, "Duncan", "Howard");
        testFindByFirstNameOrLastNameQueryDsl(userService, "Luong", "Pham");
        testFindByJoinDateBetween(userService, DateUtils.parseDate("01/01/2012", "dd/MM/yyy"), DateUtils.parseDate("31/12/2015", "dd/MM/yyy"));
        testFindByActiveTrue(userService);
        testFindByActiveFalse(userService);
        testFindUserByUsernameQuery(userService, "test5");
        ((ClassPathXmlApplicationContext) context).close();
    }

    private static void createUsers(UserService userService) throws Exception {
        List<User> users = new ArrayList<User>();

        User user1 = new User();
        user1.setUsername("test1");
        user1.setJoinDate(DateUtils.parseDate("01/01/2010", "dd/MM/yyyy"));
        user1.setFirstName("Truong");
        user1.setLastName("Pham");
        user1.setLevel((short) 1);
        users.add(user1);

        User user2 = new User();
        user2.setUsername("test2");
        user2.setJoinDate(DateUtils.parseDate("02/02/2011", "dd/MM/yyyy"));
        user2.setFirstName("Luong");
        user2.setLastName("Nguyen");
        user2.setLevel((short) 2);
        users.add(user2);

        User user3 = new User();
        user3.setUsername("test3");
        user3.setJoinDate(DateUtils.parseDate("03/03/2012", "dd/MM/yyyy"));
        user3.setFirstName("Duong");
        user3.setLastName("Bui");
        user3.setLevel((short) 3);
        users.add(user3);

        User user4 = new User();
        user4.setUsername("test4");
        user4.setJoinDate(DateUtils.parseDate("06/06/2013", "dd/MM/yyyy"));
        user4.setActive(Boolean.FALSE);
        user4.setFirstName("Duncan");
        user4.setLastName("Howard");
        user4.setLevel((short) 4);
        users.add(user4);

        User user5 = new User();
        user5.setUsername("test5");
        user5.setJoinDate(DateUtils.parseDate("05/05/2015", "dd/MM/yyyy"));
        user5.setFirstName("Thu");
        user5.setLastName("Mai");
        user5.setLevel((short) 5);
        users.add(user5);

        userService.save(users);

        System.out.println("5 users have been created!");
    }

    private static void testFindUserById(UserService userService, Long id) {
        User user = userService.findOne(id);
        if (user != null) {
            System.out.println("testFindUserById() Found user: " + user);
        } else {
            System.err.println("Can't found any users associated with the ID = " + id);
        }
    }

    private static void testFindUserByUsername(UserService userService, String username) {
        User user = userService.findByUsername(username);
        if (user != null) {
            System.out.println("testFindUserByUsername() Found user: " + user);
        } else {
            System.err.println("Username not found " + username);
        }
    }

    private static void testFindByFirstNameAndLastName(UserService userService, String firstName, String lastName) {
        List<User> users = userService.findByFirstNameAndLastName(firstName, lastName);
        if (CollectionUtils.isEmpty(users)) {
            System.out.println("testFindByFirstNameAndLastName() not found!");
        } else {
            System.out.println("testFindByFirstNameAndLastName() found " + users.size() + " matched with the criteria!");
            for (User user : users) {
                System.out.println(user);
            }
        }
    }

    private static void testFindByFirstNameOrLastName(UserService userService, String firstName, String lastName) {
        List<User> users = userService.findByFirstNameOrLastName(firstName, lastName);
        if (CollectionUtils.isEmpty(users)) {
            System.out.println("testFindByFirstNameOrLastName() not found!");
        } else {
            System.out.println("testFindByFirstNameOrLastName() found " + users.size() + " matched with the criteria!");
            for (User user : users) {
                System.out.println(user);
            }
        }
    }

    private static void testFindUserByUsernameQueryDsl(UserService userService, String username) {
        User user = userService.findUserByUsernameQueryDsl(username);
        if (user != null) {
            System.out.println("testFindUserByUsernameQueryDsl() Found user: " + user);
        } else {
            System.err.println("testFindUserByUsernameQueryDsl() username not found " + username);
        }
    }

    private static void testFindByFirstNameAndLastNameQueryDsl(UserService userService, String firstName, String lastName) {
        List<User> users = userService.findByFirstNameAndLastNameQueryDsl(firstName, lastName);
        if (CollectionUtils.isEmpty(users)) {
            System.out.println("testFindByFirstNameAndLastNameQueryDsl() not found!");
        } else {
            System.out.println("testFindByFirstNameAndLastNameQueryDsl() found " + users.size() + " matched with the criteria!");
            for (User user : users) {
                System.out.println(user);
            }
        }
    }

    private static void testFindByFirstNameOrLastNameQueryDsl(UserService userService, String firstName, String lastName) {
        List<User> users = userService.findByFirstNameOrLastNameQueryDsl(firstName, lastName);
        if (CollectionUtils.isEmpty(users)) {
            System.out.println("testFindByFirstNameOrLastNameQueryDsl() not found!");
        } else {
            System.out.println("testFindByFirstNameOrLastNameQueryDsl() found " + users.size() + " matched with the criteria!");
            for (User user : users) {
                System.out.println(user);
            }
        }
    }
    
    private static void testFindByJoinDateBetween(UserService userService, Date from, Date to) {
        List<User> users = userService.findByJoinDateBetween(from, to);
        if (CollectionUtils.isEmpty(users)) {
            System.out.println("testFindByJoinDateBetween() not found!");
        } else {
            System.out.println("testFindByJoinDateBetween() found " + users.size() + " matched with the criteria!");
            for (User user : users) {
                System.out.println(user);
            }
        }
    }
    
    private static void testFindByActiveTrue(UserService userService) {
        List<User> users = userService.findByActiveTrue();
        if (CollectionUtils.isEmpty(users)) {
            System.out.println("testFindByActiveTrue() not found!");
        } else {
            System.out.println("testFindByActiveTrue() found " + users.size() + " matched with the criteria!");
            for (User user : users) {
                System.out.println(user);
            }
        }
    }
    
    private static void testFindByActiveFalse(UserService userService) {
        List<User> users = userService.findByActiveFalse();
        if (CollectionUtils.isEmpty(users)) {
            System.out.println("testFindByActiveFalse() not found!");
        } else {
            System.out.println("testFindByActiveFalse() found " + users.size() + " matched with the criteria!");
            for (User user : users) {
                System.out.println(user);
            }
        }
    }
    
    private static void testFindUserByUsernameQuery(UserService userService, String username) {
        User user = userService.findByUsernameQuery(username);
        if (user != null) {
            System.out.println("testFindUserByUsernameQuery() Found user: " + user);
        } else {
            System.err.println("testFindUserByUsernameQuery() username not found " + username);
        }
    }
}
