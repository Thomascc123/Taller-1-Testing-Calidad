package com.bank.credit.exception;

import org.springframework.http.HttpStatus;

public class CustomerNotFoundException extends BusinessException {

    public CustomerNotFoundException(){
        super(HttpStatus.NOT_FOUND, "CUSTOMER_NOT_FOUND", "Cliente no encontrado");
    }

}
