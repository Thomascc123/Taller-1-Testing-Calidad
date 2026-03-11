package com.bank.credit.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Credit Entity Tests")
public class CreditTest {
    @Test
    @DisplayName("no-args constructor test")
    void testNoArgsConstructor() {
        // Arrange & Act
        Credit credit = new Credit();

        // Assert
        assertNotNull(credit);
        assertNull(credit.getCreditId());
    }

    @Test
    @DisplayName("Full argument constructor test")
    void testAllArgsConstructor(){
        Customer customerTest = new Customer();
        LocalDate time = LocalDateTime.of(2012, 6, 30, 12, 0).toLocalDate();

        Credit credit = new Credit(345678456L, 1234235355L, Credit.CreditType.Hipotecario, 22000.05,  0.5,time, (short)74,  (short)3543, 77000.00, (short) 7, 0.1,  Credit.CreditStatus.Activo, customerTest);

        assertEquals(345678456L,credit.getCreditId());
        assertEquals(1234235355L,credit.getCustomerId());
        assertEquals(Credit.CreditType.Hipotecario,credit.getType());
        assertEquals(22000.05,credit.getAmount());
        assertEquals(0.5,credit.getBalance());
        assertEquals(time,credit.getGrantedDate());
        assertEquals((short)74,credit.getPaymentsDay());
        assertEquals((short)3543,credit.getPaymentsNumber());
        assertEquals(77000.00,credit.getPaymentValue());
        assertEquals((short)7,credit.getPaymentsDelayed());
        assertEquals(0.1,credit.getInterestRate());
        assertEquals(Credit.CreditStatus.Activo,credit.getStatus());
        assertEquals(customerTest,credit.getCustomer());
    }

    @Test
    @DisplayName("Getter and setter test of creditId")
    void testCreditIdGetterSetter(){
        Credit credit = new Credit();

        credit.setCreditId(1234123412341234L);

        assertEquals(1234123412341234L, credit.getCreditId());
    }

    @Test
    @DisplayName("Getter and setter test of customerId")
    void testCustomerIdGetterSetter(){
        Credit credit = new Credit();

        credit.setCustomerId(4747433659887L);

        assertEquals(4747433659887L, credit.getCustomerId());
    }

    @Test
    @DisplayName("Getter and setter test of type")
    void testTypeGetterSetter(){
        Credit credit = new Credit();

        credit.setType(Credit.CreditType.Consumo);

        assertEquals(Credit.CreditType.Consumo, credit.getType());
    }

    @Test
    @DisplayName("Getter and setter test of amount")
    void testAmountGetterSetter(){
        Credit credit = new Credit();

        credit.setAmount(33456.95);

        assertEquals(33456.95, credit.getAmount());
    }

    @Test
    @DisplayName("Getter and setter test of balance")
    void testBalanceGetterSetter(){
        Credit credit = new Credit();

        credit.setBalance(0.7);

        assertEquals(0.7, credit.getBalance());
    }

    @Test
    @DisplayName("Getter and setter test of grantedDate")
    void testGrantedDateGetterSetter(){
        LocalDate date = LocalDateTime.of(2020, 7,5, 13, 25, 33).toLocalDate();
        Credit credit = new Credit();

        credit.setGrantedDate(date);

        assertEquals(date, credit.getGrantedDate());
    }

    @Test
    @DisplayName("Getter and setter test of paymentsDay")
    void testPaymentsDayGetterSetter(){
        Credit credit = new Credit();

        credit.setPaymentsDay((short)10);

        assertEquals((short)10, credit.getPaymentsDay());
    }

    @Test
    @DisplayName("Getter and setter test of paymentsNumber")
    void testPaymentsNumberGetterSetter(){
        Credit credit = new Credit();

        credit.setPaymentsNumber((short)167);

        assertEquals((short)167, credit.getPaymentsNumber());
    }

    @Test
    @DisplayName("Getter and setter test of paymentValue")
    void testPaymentValueGetterSetter(){
        Credit credit = new Credit();

        credit.setPaymentValue(5500.00);

        assertEquals(5500.00, credit.getPaymentValue());
    }

    @Test
    @DisplayName("Getter and setter test of paymentsDelayed")
    void testPaymentsDelayedGetterSetter(){
        Credit credit = new Credit();

        credit.setPaymentsDelayed((short)24);

        assertEquals((short)24, credit.getPaymentsDelayed());
    }

    @Test
    @DisplayName("Getter and setter test of creditId")
    void testInterestRateGetterSetter(){
        Credit credit = new Credit();

        credit.setInterestRate(0.08);

        assertEquals(0.08, credit.getInterestRate());
    }

    @Test
    @DisplayName("Getter and setter test of Status")
    void testStatusGetterSetter(){
        Credit credit = new Credit();

        credit.setStatus(Credit.CreditStatus.En_Revision);

        assertEquals(Credit.CreditStatus.En_Revision, credit.getStatus());
    }

    @Test
    @DisplayName("Getter and setter test of customer")
    void testCustomerTestGetterSetter(){
        Credit credit = new Credit();
        Customer customerTest = new Customer();

        credit.setCustomer(customerTest);

        assertEquals(customerTest, credit.getCustomer());
    }

    @Test
    @DisplayName("Testing null assignments in properties")
    void testNullValues(){
        Credit credit = new Credit();

        credit.setCreditId(null);
        credit.setCustomerId(null);
        credit.setType(null);
        credit.setAmount(null);
        credit.setBalance(null);
        credit.setGrantedDate(null);
        credit.setPaymentsDay(null);
        credit.setPaymentsNumber(null);
        credit.setPaymentValue(null);
        credit.setPaymentsDelayed(null);
        credit.setInterestRate(null);
        credit.setStatus(null);
        credit.setCustomer(null);

        assertNull(credit.getCreditId());
        assertNull(credit.getCustomerId());
        assertNull(credit.getType());
        assertNull(credit.getAmount());
        assertNull(credit.getBalance());
        assertNull(credit.getGrantedDate());
        assertNull(credit.getPaymentsDay());
        assertNull(credit.getPaymentsNumber());
        assertNull(credit.getPaymentValue());
        assertNull(credit.getPaymentsDelayed());
        assertNull(credit.getInterestRate());
        assertNull(credit.getStatus());
        assertNull(credit.getCustomer());
    }
}
