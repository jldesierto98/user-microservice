package com.user.test.User.Service;

import com.user.test.User.Service.exception.AccountNotFoundException;
import com.user.test.User.Service.exception.AccountSaveFailedException;
import com.user.test.User.Service.exception.EmptyEmailException;
import com.user.test.User.Service.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleEmptyEmailException(MethodArgumentNotValidException ex){
        String errorMessage = ex.getBindingResult().getFieldError("customerEmail").getDefaultMessage();
        EmptyEmailException errorResponse = new EmptyEmailException(HttpStatus.BAD_REQUEST.value(), errorMessage);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Object> handleAccountNotFoundException(AccountNotFoundException ex) {
        // Create a custom JSON response
        CustomErrorResponse errorResponse = new CustomErrorResponse(401, "Customer not found");

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    // Custom error response class
    static class CustomErrorResponse {
        private int transactionStatusCode;
        private String transactionStatusDescription;

        public CustomErrorResponse(int transactionStatusCode, String transactionStatusDescription) {
            this.transactionStatusCode = transactionStatusCode;
            this.transactionStatusDescription = transactionStatusDescription;
        }

        // Getters and setters
        public int getTransactionStatusCode() {
            return transactionStatusCode;
        }

        public void setTransactionStatusCode(int transactionStatusCode) {
            this.transactionStatusCode = transactionStatusCode;
        }

        public String getTransactionStatusDescription() {
            return transactionStatusDescription;
        }

        public void setTransactionStatusDescription(String transactionStatusDescription) {
            this.transactionStatusDescription = transactionStatusDescription;
        }
    }
}
