package com.bank.credit.service;

import com.bank.credit.entity.User;
import com.bank.credit.exception.InvalidPasswordException;
import com.bank.credit.exception.UserNotFoundException;
import com.bank.credit.repository.UserRepository;
import com.bank.credit.util.Encryption;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUser(Long userId){
        return userRepository.findById(userId)
                                 .orElseThrow(() -> new RuntimeException("user not found"));
    }

    public User addUser(Long customerId, String email, String password, User.UserRole role){

        User user = new User();
        user.setCustomerId(customerId);
        user.setEmail(email);
        user.setPassword(Encryption.encryptWord(password));
        user.setRole(role);

        return userRepository.save(user);

    }

    public User Login(String userEmail, String userPasword){
        
        try{
            User user = userRepository.findByEmail(userEmail);
            if(!Encryption.checkPassword(userPasword, user.getPassword())){
                throw new InvalidPasswordException();
            } else{
                return user;
            }
        } catch(Exception e){
            throw new UserNotFoundException();
        }
    }
}
