package com.bank.credit.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import com.bank.credit.entity.Credit;
import com.bank.credit.entity.Credit.CreditType;
import com.bank.credit.entity.Customer;
import com.bank.credit.entity.Customer.CustomerStatus;
import com.bank.credit.exception.CustomerNotFoundException;
import com.bank.credit.repository.CreditRepository;
import com.bank.credit.repository.CustomerRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreditServiceTest {

    @Mock
    private CreditRepository creditRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CreditService creditService;

    private Customer activeCustomer;

    @BeforeEach
    void setup() {
        activeCustomer = new Customer();
        activeCustomer.setCustomerId(1L);
        activeCustomer.setStatus(CustomerStatus.Activo);
    }

    @Test
    void shouldReturnCreditsByType() {
        //Arrange
        Credit credit1 = new Credit();
        credit1.setCreditId(1L);
        credit1.setType(CreditType.Consumo);

        Credit credit2 = new Credit();
        credit1.setCreditId(2L);
        credit2.setType(CreditType.Consumo);

        Credit credit3 = new Credit();
        credit3.setCreditId(3L);
        credit3.setType(CreditType.Consumo);

        List<Credit> credits = List.of(credit1,credit2,credit3);

        when(creditRepository.findAllCreditsByType("Consumo")).thenReturn(credits);

        //Act
        List<Credit> result = creditService.findAllCreditsByType("Consumo");

        //Assert
        assertEquals(3, result.size());
        assertEquals(CreditType.Consumo, result.get(0).getType());
        assertEquals(CreditType.Consumo, result.get(1).getType());
        assertEquals(CreditType.Consumo, result.get(2).getType());
    }

    @Test
    void shouldGrantCreditSuccessfully() {
        //Arrange
        when(customerRepository.findById(1L)).thenReturn(Optional.of(activeCustomer));
        when(creditRepository.save(any(Credit.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Credit result = creditService.grantCredit(
                1L,
                CreditType.Consumo,
                10000.0,
                (short) 12
        );

        assertNotNull(result);
        assertEquals(1L, result.getCustomerId());
        assertEquals(CreditType.Consumo, result.getType());
        assertEquals(10000.0, result.getAmount());
        assertEquals(Credit.CreditStatus.Activo, result.getStatus());

        verify(customerRepository).findById(1L);
        verify(creditRepository).save(any(Credit.class));
    }

    @Test
    void shouldThrowExceptionWhenCustomerNotFound() {

        when(customerRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                CustomerNotFoundException.class,
                () -> creditService.grantCredit(1L, CreditType.Consumo, 10000.0, (short) 12)
        );

        verify(customerRepository).findById(1L);
        verifyNoInteractions(creditRepository);
    }

    @Test
    void shouldThrowExceptionWhenCustomerInactive() {

        Customer inactiveCustomer = new Customer();
        inactiveCustomer.setCustomerId(1L);
        inactiveCustomer.setStatus(CustomerStatus.Inactivo);

        when(customerRepository.findById(1L))
                .thenReturn(Optional.of(inactiveCustomer));

        assertThrows(
                RuntimeException.class,
                () -> creditService.grantCredit(1L, CreditType.Consumo, 10000.0, (short) 12)
        );
    }

    @Test
    void shouldThrowExceptionWhenCustomerIsDefaulter() {

        Customer defaulter = new Customer();
        defaulter.setCustomerId(1L);
        defaulter.setStatus(CustomerStatus.Moroso);

        when(customerRepository.findById(1L))
                .thenReturn(Optional.of(defaulter));

        assertThrows(
                RuntimeException.class,
                () -> creditService.grantCredit(1L, CreditType.Consumo, 10000.0, (short) 12)
        );
    }


}
