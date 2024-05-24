package com.user.test.User.Service.service;

import com.user.test.User.Service.request.CreateAccountRequest;
import com.user.test.User.Service.response.AccountResponse;
import com.user.test.User.Service.response.CustomerInfoResponse;

public interface AccountService {

    /**
     * Creates a new account based on the provided account creation request.
     *
     * @param request
     * @return CreateAccountResponse
     */
    AccountResponse createAccount(CreateAccountRequest request);

    CustomerInfoResponse getCustomerByCustomerNumber(Long customerNumber);
}
