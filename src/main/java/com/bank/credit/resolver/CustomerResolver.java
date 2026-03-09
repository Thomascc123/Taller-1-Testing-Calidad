package com.bank.credit.resolver;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.bank.credit.entity.Customer;
import com.bank.credit.service.CustomerService;

@Controller
public class CustomerResolver {
    private final CustomerService customerService;

    public CustomerResolver(CustomerService customerService) {
        this.customerService = customerService;
    }

    @QueryMapping
    public List<Customer> allCustomers(){
        return customerService.getAllCustomers();
    }

    @QueryMapping
    public Customer customerById(@Argument Long customerId){
        return customerService.getCustomer(customerId);
    }

    @MutationMapping
    public Customer addCustomer(@Argument String name, @Argument String lastName, 
                                @Argument String email, @Argument String phoneNumber, 
                                @Argument String documentType, @Argument String documentNumber){

        return customerService.addCustomer(name, lastName, email, phoneNumber, documentType, documentNumber);
    }

}
