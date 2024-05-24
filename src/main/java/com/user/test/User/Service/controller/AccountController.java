package com.user.test.User.Service.controller;


import com.user.test.User.Service.response.CustomerInfoResponse;
import com.user.test.User.Service.service.AccountService;
import com.user.test.User.Service.request.CreateAccountRequest;
import com.user.test.User.Service.response.AccountResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/account")
@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@Valid  @RequestBody CreateAccountRequest request) {
        return new ResponseEntity<>(accountService.createAccount(request), HttpStatus.CREATED);
    }

    @GetMapping("/{customerNumber}")
    public ResponseEntity<CustomerInfoResponse> getCustomer(@PathVariable Long customerNumber){
        return new ResponseEntity<>(accountService.getCustomerByCustomerNumber(customerNumber), HttpStatus.FOUND);
    }



}
