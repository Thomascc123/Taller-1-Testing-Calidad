package com.bank.credit.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.bank.credit.entity.Customer.CustomerStatus;
import com.bank.credit.entity.Customer.DocumentType;
import com.bank.credit.entity.User.UserRole;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("User entity tests")
public class UserTest {

    @Test
    @DisplayName("No arguments constructor test")
    public void noArgsConstructorTest(){
        User userEntity = new User();
        
        assertNotNull(userEntity);
        assertNull(userEntity.getUserId());
    }

    @Test
    @DisplayName("All arguments constructor test")
    public void allArgsConstructorTest(){
        User userEntity = new User(1L, 2L, "rodrigo_gutierrez2020@hotmail.com", "superPsswordRodrigoD_1234",UserRole.Usuario);

        assertEquals(1L, userEntity.getUserId());
        assertEquals(2L, userEntity.getCustomerId());
        assertEquals("rodrigo_gutierrez2020@hotmail.com", userEntity.getEmail());
        assertEquals("superPsswordRodrigoD_1234", userEntity.getPassword());
        assertEquals(UserRole.Usuario, userEntity.getRole());
    }

    @Test
    @DisplayName("Setter and getter test for userId")
    public void getterAndSetterTestUserId(){
        User userEntity = new User();

        userEntity.setUserId(3L);

        assertEquals(3L, userEntity.getUserId());
    }

    @Test
    @DisplayName("Setter and getter test for customerId")
    public void getterAndSetterTestCustomerId(){
        User userEntity = new User();

        userEntity.setCustomerId(1L);

        assertEquals(1L, userEntity.getUserId());
    }

    @Test
    @DisplayName("Setter and getter test for email")
    public void getterAndSetterTestEmail(){
        User userEntity = new User();

        userEntity.setEmail("montoya.arango.jf@institution.com");

        assertEquals("montoya.arango.jf@institution.com", userEntity.getEmail());
    }

    @Test
    @DisplayName("Setter and getter test for password")
    public void getterAndSetterTestPassword(){
        User userEntity = new User();

        userEntity.setPassword("u1tr4_s3cur3!!p455w0rd");

        assertEquals("u1tr4_s3cur3!!p455w0rd", userEntity.getPassword());
    }

    @Test
    @DisplayName("Setter and getter test for role")
    public void getterAndSetterTestRole(){
        User userEntity = new User();

        userEntity.setRole(UserRole.Administrador);

        assertEquals(UserRole.Administrador, userEntity.getRole());
    }
}
