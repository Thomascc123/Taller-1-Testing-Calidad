package com.bank.credit.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


import com.bank.credit.entity.Customer;
import com.bank.credit.exception.CustomerNotFoundException;
import com.bank.credit.repository.CustomerRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest{
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;


    @Test
    void shoudReturnAllCustomers(){
        //Arrange
        Customer customer1 = new Customer();
        customer1.setCustomerId(1L);

        Customer customer2 = new Customer();
        customer2.setCustomerId(2L);

        Customer  customer3 = new Customer();
        customer3.setCustomerId(3L);

        List<Customer> customers = List.of(customer1, customer2, customer3);

        when(customerService.getAllCustomers()).thenReturn(customers);

        //Act
        List<Customer> result =  customerService.getAllCustomers();

        //Assert
        assertEquals(result, customers);
        assertEquals(3, result.size());
    }

    @Test
    void nullListReturnAllCustomers(){
        //Arrange
        when(customerService.getAllCustomers()).thenReturn(Collections.emptyList());
        //Act
        List<Customer> result = customerService.getAllCustomers();
        //Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shoudReturnCustomerWithId(){
        //Arrange
        Customer customer = new Customer();
        customer.setCustomerId(1L);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        //Act
        Customer result = customerService.getCustomer(1L);

        //Assert
        assertEquals(result, customer);
    }

    @Test
    void ShoudReturnExceptionWhenNullCustomer(){
        //Arrange
        when(customerRepository.findById(55L)).thenReturn(Optional.empty());

        //Act
        RuntimeException thrown = assertThrows(CustomerNotFoundException.class, () -> {
            customerService.getCustomer(55L);
        });

        //Assert
        assertEquals("Cliente no encontrado", thrown.getMessage());
    }


}