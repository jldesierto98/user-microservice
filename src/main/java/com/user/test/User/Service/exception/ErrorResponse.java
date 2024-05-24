package com.user.test.User.Service.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String transactionCode;
    private String transactionStatusDescription;

    public ErrorResponse(String transactionCode, String transactionStatusDescription) {
        this.transactionCode = transactionCode;
        this.transactionStatusDescription = transactionStatusDescription;
    }

}
