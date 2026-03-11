package com.bank.credit.exception;

import org.springframework.http.HttpStatus;
public class CustomerHasUserException extends BusinessException  {
    public CustomerHasUserException(){
        super(HttpStatus.CONFLICT, "CUSTOMER_HAS_USER", "the customer already has an user");
    }
}
