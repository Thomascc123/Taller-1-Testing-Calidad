package com.bank.credit.exception;

import org.springframework.http.HttpStatus;

public class InactiveCustomerException extends BusinessException{
    public InactiveCustomerException(){
        super(HttpStatus.CONFLICT, "INACTIVE_CUSTOMER", "Cliente inactivo");
    }
}
