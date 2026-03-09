package com.bank.credit.exception;

import org.springframework.http.HttpStatus;

public class InvalidDocumentTypeException extends BusinessException{
    public InvalidDocumentTypeException(){
        super(HttpStatus.BAD_REQUEST,"INVALID_DOCUMENT_TYPE", "Tipo de documento no válido");
    }
}