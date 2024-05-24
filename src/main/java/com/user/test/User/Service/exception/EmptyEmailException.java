package com.user.test.User.Service.exception;

public class EmptyEmailException extends RuntimeException{

    private int transactionStatusCode;
    private String transactionStatusDescription;

    public EmptyEmailException(int transactionStatusCode, String transactionStatusDescription) {
        this.transactionStatusCode = transactionStatusCode;
        this.transactionStatusDescription = transactionStatusDescription;
    }
}
