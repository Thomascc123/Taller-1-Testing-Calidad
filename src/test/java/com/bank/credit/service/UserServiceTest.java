package com.bank.credit.service;

import static org.junit.jupiter.api.Assertions.*;

import com.bank.credit.entity.User;
import com.bank.credit.exception.CustomerHasUserException;
import com.bank.credit.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService usertService;

    @Mock
    private UserRepository userRepository;

    @Test
    void TestGetAllUsers(){
        //Arrange
        User user1 = new User();
        user1.setUserId(1l);

        User user2 = new User();
        user2.setUserId(2l);

        User user3 = new User();
        user3.setUserId(3l);

        List<User> users = List.of(user1, user2, user3);

        when(usertService.getAllUsers()).thenReturn(users);

        //Act
        List<User> result = usertService.getAllUsers();

        //Assert
        assertEquals(users, result);
        assertEquals(3, result.size());

    }

    @Test
    void TestGetAllUsersWithEmptyUsers(){
        //Arrange

        when(usertService.getAllUsers()).thenReturn(Collections.emptyList());

        //Act
        List<User> result = usertService.getAllUsers();

        //Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetUserById(){
        //Arrange
        User user = new User();
        user.setUserId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        //Act
        User result = usertService.getUser(1L);

        //Asser
        assertEquals(result, user);
    }

    @Test
    void testGetNullUserById(){
        //Arrange
        when(userRepository.findById(55L)).thenReturn(Optional.empty());

        //Act
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            usertService.getUser(55L);
        });

        //Asser
        assertEquals("Usuario no encontrado", thrown.getMessage());
    }

    @Test
    void testAddUser(){
        //Arrange
        User userAdded = new User();
        userAdded.setUserId(1L);
        userAdded.setEmail("alvarez@gmail.com");
        userAdded.setPassword("aswty*34");
        userAdded.setCustomerId(1L);
        userAdded.setRole(User.UserRole.USUARIO);

        when(userRepository.save(any(User.class))).thenReturn(userAdded);

        //Act
        User result = usertService.addUser(1L,"alvarez@gmail.com","aswty*34",User.UserRole.USUARIO);

        //Assert
        assertNotNull(result);
        assertEquals(1L, result.getUserId());
    }

    @Test
    void testAddUserNull(){
        //Arrange

        User existingUser = new User();
        existingUser.setUserId(1L);

        when(userRepository.findByCustomerId(1L))
                .thenReturn(Optional.of(existingUser));

        //Act

        RuntimeException thrown = assertThrows(CustomerHasUserException.class, () -> {
            usertService.addUser(1L, "montoya@mail.com", "bdfd45.>>3", User.UserRole.USUARIO);
        });

        //Assert
        assertEquals("the customer already has an user", thrown.getMessage());
    }


}
