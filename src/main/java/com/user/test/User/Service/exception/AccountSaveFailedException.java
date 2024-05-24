package com.user.test.User.Service.exception;

public class AccountSaveFailedException extends  RuntimeException{

    public AccountSaveFailedException(){
        super(String.format("Sorry account save has failed. Please try again later."));
    }
}
