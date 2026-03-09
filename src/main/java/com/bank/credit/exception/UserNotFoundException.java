package com.bank.credit.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BusinessException {

    public UserNotFoundException(){
        super(HttpStatus.NOT_FOUND, "USER_NOT_FOUND", "Usuario no encontrado");
    }

}

