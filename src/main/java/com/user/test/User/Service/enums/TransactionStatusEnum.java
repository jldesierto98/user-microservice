package com.user.test.User.Service.enums;

public enum TransactionStatusEnum {
    SUCCESS(201, "Customer account created!");

    private final int statusCode;
    private final String statusDescription;

    TransactionStatusEnum(int statusCode, String statusDescription) {
        this.statusCode = statusCode;
        this.statusDescription = statusDescription;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }
}
