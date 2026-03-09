package com.bank.credit.exception;

import org.springframework.http.HttpStatus;

public class DefaulterCustomerException extends BusinessException {
    public DefaulterCustomerException(){
        super(HttpStatus.CONFLICT, "DEFAULTER_CUSTOMER", "Cliente en mora");
    }
}
