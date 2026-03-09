package com.bank.credit.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bank.credit.entity.Credit;
import com.bank.credit.entity.Credit.CreditStatus;
import com.bank.credit.entity.Credit.CreditType;
import com.bank.credit.entity.Customer;
import com.bank.credit.entity.Customer.CustomerStatus;
import com.bank.credit.exception.CustomerNotFoundException;
import com.bank.credit.exception.DefaulterCustomerException;
import com.bank.credit.exception.InactiveCustomerException;
import com.bank.credit.exception.InvalidCreditTypeException;
import com.bank.credit.repository.CreditRepository;
import com.bank.credit.repository.CustomerRepository;

@Service
public class CreditService {

    private final CreditRepository creditRepository;
    private final CustomerRepository customerRepository;

    public CreditService(CreditRepository creditRepository, CustomerRepository customerRepository) {
        this.creditRepository = creditRepository;
        this.customerRepository = customerRepository;
    }

    public List<Credit> findAllCreditsByType(String type){
        return creditRepository.findAllCreditsByType(type);
    }

    public Credit grantCredit(Long customerId, CreditType type, Double amount, Short paymentsNumber){
        Customer customer = customerRepository.findById(customerId)
        .orElseThrow(CustomerNotFoundException::new);

        if (!customer.getStatus().equals(CustomerStatus.Activo)){
            throw new InactiveCustomerException();
        }

        if (customer.getStatus().equals(CustomerStatus.Moroso)){
            throw new DefaulterCustomerException();
        }

        Double interestRate = computeInterestRate(type); 
        if(interestRate == null){
            throw new InvalidCreditTypeException();
        }

        Double paymentValue = computePaymentValue(amount, interestRate, paymentsNumber);

        LocalDateTime grantedDate = LocalDateTime.now();
        Short paymentsDay = (short) (grantedDate.getDayOfMonth());
        if(paymentsDay >30){
            paymentsDay = 30;
        }
        Short paymentsDelayed = 0;

        Credit credit = new Credit();
        credit.setCustomerId(customerId);
        credit.setType(type);
        credit.setAmount(amount);
        credit.setBalance(amount);
        credit.setGrantedDate(grantedDate);
        credit.setPaymentsDay(paymentsDay);
        credit.setPaymentsNumber(paymentsNumber);
        credit.setPaymentsDelayed(paymentsDelayed);
        credit.setPaymentValue(paymentValue);
        credit.setInterestRate(interestRate);
        credit.setStatus(CreditStatus.Activo);

        return creditRepository.save(credit);

    }

    private Double computeInterestRate(CreditType type){
        /*
        Returns the nominal annual interest rate for the credit
        according the given type as a percentage in fraction form
         */
        switch(type){
            case Leasing: return 0.18;
            case Hipotecario: return 0.15;
            case Consumo: return 0.30;
            case Educativo: return 0.13;
            case Negocio: return 0.12;
            
            default: return null;
        }
    }

    private Double computePaymentValue(Double amount, Double interestRate, Short paymentsNumber){
        Double monthlyRate = interestRate/12;
        Double powerBase = 1 + monthlyRate;
        Double power = Math.pow(powerBase, paymentsNumber);

        return  amount*monthlyRate*power/(power - 1);
    }

}
