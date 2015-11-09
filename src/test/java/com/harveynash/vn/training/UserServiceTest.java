package com.harveynash.vn.training;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.util.CollectionUtils;

import com.harveynash.vn.training.dao.UserRepository;
import com.harveynash.vn.training.model.User;
import com.harveynash.vn.training.service.UserService;
import com.harveynash.vn.training.service.impl.UserServiceImpl;
import com.harveynash.vn.training.utils.MyException;
import com.harveynash.vn.training.utils.ReflectionTestUtils;

public class UserServiceTest {

    @Mock
    private UserRepository repository;

    private UserService service;

//     @Mock
    @Spy
    private List<String> employees = new ArrayList<String>();

    private UserRepository repository1;

    private UserService service1;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        service = new UserServiceImpl();

        ReflectionTestUtils.setField(service, "repository", repository);

        repository1 = Mockito.spy(UserRepository.class);

        service1 = new UserServiceImpl();

        ReflectionTestUtils.setField(service1, "repository", repository1);
    }

    @Test
    public void testSaveAListOfUsers() {
        List<User> users = new LinkedList<User>();

        User user1 = new User();
        user1.setUsername("user1");
        users.add(user1);

        User user2 = new User();
        user2.setUsername("user2");
        users.add(user2);

        when(repository.save(users)).thenReturn(users);

        List<User> savedUsers = service.save(users);

        Assert.assertFalse(CollectionUtils.isEmpty(savedUsers));
        Assert.assertTrue(savedUsers.size() == 2);
        Assert.assertEquals("user2", savedUsers.get(1).getUsername());

        verify(repository, Mockito.times(1)).save(users);
    }

    @Test
    public void testSaveAnUser() {
        User user = new User();
        user.setUsername("something");

        when(repository.saveAndFlush(user)).thenReturn(user);

        User savedUser = service.save(user);

        Assert.assertNotNull(savedUser);
        Assert.assertEquals("something", savedUser.getUsername());

        verify(repository, Mockito.atLeastOnce()).saveAndFlush(user);
    }

    @Test
    public void testFindOne() {
        User user = new User();
        user.setId(1L);
        user.setUsername("test");

        when(repository.findOne(1L)).thenReturn(user);

        Assert.assertNotNull(service.findOne(1L));
        Assert.assertTrue(service.findOne(1L).getId() == 1);
        Assert.assertEquals("test", service.findOne(1L).getUsername());

        verify(repository, Mockito.times(3)).findOne(1L);
    }

    @Test
    public void testFindByFirstNameOrLastName() {
        List<User> users = new LinkedList<User>();

        User user1 = new User();
        user1.setUsername("user1");
        users.add(user1);
        user1.setFirstName("test");

        User user2 = new User();
        user2.setUsername("user2");
        users.add(user2);
        user2.setLastName("test");

        when(repository.findByFirstNameOrLastName("test", "test")).thenReturn(users);

        List<User> results = service.findByFirstNameOrLastName("test", "test");

        Assert.assertFalse(CollectionUtils.isEmpty(results));
        Assert.assertTrue(results.size() == 2);
        Assert.assertEquals("user2", results.get(1).getUsername());

        verify(repository, Mockito.times(1)).findByFirstNameOrLastName("test", "test");
    }

    @Test
    public void testFindByActiveTrue() {
        List<User> users = new LinkedList<User>();

        User user1 = new User();
        user1.setUsername("user1");
        users.add(user1);
        user1.setFirstName("test1");

        User user2 = new User();
        user2.setUsername("user2");
        users.add(user2);
        user2.setLastName("test2");

        when(repository.findByActiveTrue()).thenReturn(users);

        List<User> results = service.findByActiveTrue();

        Assert.assertFalse(CollectionUtils.isEmpty(results));
        Assert.assertTrue(results.size() == 2);
        Assert.assertEquals("test2", results.get(1).getLastName());

        verify(repository, Mockito.times(1)).findByActiveTrue();
    }

    @SuppressWarnings("unchecked")
    @Test(expected = Exception.class)
    public void testMethodWillThrowAnException() {
        when(repository.findAll()).thenThrow(Exception.class);
        repository.findAll();
    }

    @Test
    public void testVoidMethod() {
        doNothing().when(repository1).saySomething(anyString());
        service1.saySomething("Barack Obama");

        doThrow(MyException.class).when(repository1).saySomething("Xi Jinping");
        service1.saySomething("Xi Jinping");
    }

    @Test
    public void testStubbing() {
//         when(employees.get(0)).thenReturn("something");
        doReturn("something").when(employees).get(0);

        String value = employees.get(0);

        Assert.assertEquals("something", value);

        verify(employees, Mockito.atLeastOnce()).get(0);
    }
}
