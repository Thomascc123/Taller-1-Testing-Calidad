package com.bank.credit.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.bank.credit.entity.Customer.CustomerStatus;
import com.bank.credit.entity.Customer.DocumentType;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Customer entity tests")
public class CustomerTest {

    @Test
    @DisplayName("No arguments constructor test")
    public void noArgsConstructorTest(){
        Customer customerEntity = new Customer();
        
        assertNotNull(customerEntity);
        assertNull(customerEntity.getCustomerId());
    }

    @Test
    @DisplayName("Full arguments constructor test")
    public void allArgsConstructorTest(){
        Customer customerEntity = new Customer(1L, "Emiliano", "Gonzalez", "emil_gonzalez@gmail.com", "3146548792",DocumentType.CEDULA_CIUDADANIA,"1019542365",CustomerStatus.Activo);

        assertNotNull(customerEntity);
        assertEquals(1L, customerEntity.getCustomerId());
        assertEquals("Emiliano", customerEntity.getName());
        assertEquals("Gonzalez",customerEntity.getLastName());
        assertEquals("emil_gonzalez@gmail.com", customerEntity.getEmail());
        assertEquals("3146548792", customerEntity.getPhoneNumber());
        assertEquals(DocumentType.CEDULA_CIUDADANIA, customerEntity.getDocumentType());
        assertEquals("1019542365", customerEntity.getDocumentNumber());
        assertEquals(CustomerStatus.Activo, customerEntity.getStatus());

    }

    @Test
    @DisplayName("Setter and getter test for customerId")
    public void getterAndSetterTestCustomerId(){
        Customer customerEntity = new Customer();

        customerEntity.setCustomerId(10L);

        assertEquals(10L, customerEntity.getCustomerId());
    }

    @Test
    @DisplayName("Setter and getter test for name")
    public void getterAndSetterTestName(){
        Customer customerEntity = new Customer();

        customerEntity.setName("Mariana");

        assertEquals("Mariana", customerEntity.getName());
    }

    @Test
    @DisplayName("Setter and getter test for last name")
    public void getterAndSetterTestLastname(){
        Customer customerEntity = new Customer();

        customerEntity.setLastName("Hernández");

        assertEquals("Hernández", customerEntity.getLastName());
    }

    @Test
    @DisplayName("Setter and getter test for phone number")
    public void getterAndSetterTestPhoneNumber(){
        Customer customerEntity = new Customer();

        customerEntity.setPhoneNumber("3103203040");

        assertEquals("3103203040", customerEntity.getPhoneNumber());
    }

    @Test
    @DisplayName("Setter and getter test for document type")
    public void getterAndSetterTestDocumentType(){
        Customer customerEntity = new Customer();

        customerEntity.setDocumentType(DocumentType.PASAPORTE);

        assertEquals(DocumentType.PASAPORTE, customerEntity.getDocumentType());
    }

    @Test
    @DisplayName("Setter and getter test for document number")
    public void getterAndSetterDocumentNumber(){
        Customer customerEntity = new Customer();

        customerEntity.setDocumentNumber("10192354010");

        assertEquals("10192354010", customerEntity.getDocumentNumber());
    }

    @Test
    @DisplayName("Setter and getter test for customer status")
    public void getterAndSetterCustomerStatus(){
        Customer customerEntity = new Customer();

        customerEntity.setStatus(CustomerStatus.Inactivo);

        assertEquals(CustomerStatus.Moroso, customerEntity.getStatus());
    }

}

