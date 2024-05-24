package com.user.test.User.Service.exception;

public class AccountNotFoundException extends RuntimeException{


    public AccountNotFoundException(String message){
        super(message);
    }
}
