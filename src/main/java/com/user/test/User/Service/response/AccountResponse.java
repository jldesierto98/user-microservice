package com.user.test.User.Service.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccountResponse {

    private Long customerNumber;

    private Integer transactionStatusCode;

    private String transactionStatusDescription;
}
