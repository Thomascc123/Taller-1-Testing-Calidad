package com.bank.credit.resolver;

import com.bank.credit.entity.User;
import com.bank.credit.entity.User.UserRole;
import com.bank.credit.entity.Customer;
import com.bank.credit.service.UserService;
import com.bank.credit.service.CustomerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserResolver {
    private final UserService userService;
    private final CustomerService customerService;

    public UserResolver(UserService userService, CustomerService customerService) {
        this.userService = userService;
        this.customerService = customerService;
    }

    @QueryMapping
    public List<User> allUsers(){
        return userService.getAllUsers();
    }

    @QueryMapping
    public User userById(@Argument Long userId){
        return userService.getUser(userId);
    }

    @MutationMapping
    public User addUser(@Argument Long customerId, @Argument String email,
                                @Argument String password, @Argument User.UserRole role){
                                                               
        return userService.addUser(customerId, email, password, role);
    }

    @MutationMapping
    public User registerCustomer(@Argument String name, @Argument String lastName, @Argument String email,
                                 @Argument String password, @Argument String phoneNumber, 
                                 @Argument String documentType, @Argument String documentNumber){
        
        Customer customer = customerService.addCustomer(name, lastName, email, phoneNumber, documentType, documentNumber);
        
        User user = userService.addUser(customer.getCustomerId(), email, password, UserRole.Usuario);
        user.setCustomer(customer);
        return user;

    }

    @QueryMapping
    public User Login(@Argument String email, @Argument String password){
        return userService.Login(email, password);
    }
}
