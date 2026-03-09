package com.bank.credit.exception;

import org.springframework.http.HttpStatus;

public class InvalidCreditTypeException extends BusinessException{
    public InvalidCreditTypeException(){
        super(HttpStatus.BAD_REQUEST,"INVALID_CREDIT_TYPE", "Tipo de crédito no válido");
    }
}
