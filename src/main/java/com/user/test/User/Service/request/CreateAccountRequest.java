package com.user.test.User.Service.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CreateAccountRequest {

    private String customerName;

    private String customerMobile;

    @Email(message = "Invalid email format")
    private String customerEmail;

    private String address1;

    private String address2;

    @Pattern(regexp = "[CS]", message = "Invalid account type. Must be 'C' or 'S'.")
    private String accountType;

}
