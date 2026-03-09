package com.bank.credit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bank.credit.entity.Customer;
import com.bank.credit.entity.Customer.CustomerStatus;
import com.bank.credit.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer getCustomer(Long customerId){
        return customerRepository.findById(customerId)
                                 .orElseThrow(() -> new RuntimeException("customer not found"));
    }

    public Customer addCustomer(String name, String lastName, String email, String phoneNumber, 
                                String documentType, String documentNumber){
            
        Customer customer = new Customer();
        customer.setName(name);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPhoneNumber(phoneNumber);
        customer.setDocumentType(documentType);
        customer.setDocumentNumber(documentNumber);
        customer.setStatus(CustomerStatus.Activo);

        return customerRepository.save(customer);

    }

}
