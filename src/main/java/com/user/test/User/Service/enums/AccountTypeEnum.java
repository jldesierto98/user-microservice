package com.user.test.User.Service.enums;

public enum AccountTypeEnum {

    SAVINGS("S", "SAVINGS"),
    CHECKING("C", "CHECKING");

    private final String code;
    private final String accountType;

    AccountTypeEnum(String code, String accountType) {
        this.code = code;
        this.accountType = accountType;
    }

    public String getCode() {
        return code;
    }

    public String getAccountType() {
        return accountType;
    }
}
