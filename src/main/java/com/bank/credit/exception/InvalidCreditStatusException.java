package com.bank.credit.exception;

import org.springframework.http.HttpStatus;

public class InvalidCreditStatusException extends BusinessException{
    
    public InvalidCreditStatusException(){
        super(HttpStatus.BAD_REQUEST,"INVALID_DOCUMENT_TYPE", "Estado de crédito no válido");
    }
}
