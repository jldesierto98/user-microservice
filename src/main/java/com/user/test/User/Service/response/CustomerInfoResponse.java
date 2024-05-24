package com.user.test.User.Service.response;

import com.user.test.User.Service.entity.Savings;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerInfoResponse {

    private Long customerNumber;

    private String customerName;

    private String customerMobile;

    private String customerEmail;

    private String address1;

    private String address2;

    private Savings savings;

    private String transactionStatusCode;

    private String transactionStatusDescription;
}
