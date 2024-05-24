package com.user.test.User.Service.service;

import com.user.test.User.Service.entity.Account;

import com.user.test.User.Service.entity.Savings;
import com.user.test.User.Service.enums.AccountTypeEnum;
import com.user.test.User.Service.enums.TransactionStatusEnum;
import com.user.test.User.Service.exception.AccountNotFoundException;
import com.user.test.User.Service.exception.AccountSaveFailedException;
import com.user.test.User.Service.repository.AccountRepository;
import com.user.test.User.Service.repository.SavingsRepository;
import com.user.test.User.Service.request.CreateAccountRequest;
import com.user.test.User.Service.response.AccountResponse;
import com.user.test.User.Service.response.CustomerInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements  AccountService{

  private final AccountRepository accountRepository;
  private final SavingsRepository savingsRepository;

    private static final SecureRandom secureRandom = new SecureRandom();

  private static final String SAVINGS = "S";
  private static final String CHECKING = "C";
  private static final Integer BAD_REQUEST = 400;
  private static final Integer ACCOUNT_FOUND = 302;
  private static final String EMPTY_EMAIL_MESSAGE = "Email is required field";

    /**
     * Account registration process.
     * @param request
     * @return CreateAccountResponse
     */
    @Override
    public AccountResponse createAccount(CreateAccountRequest request) {

        // perform validation of fields before proceeding to process.
        if(request.getCustomerEmail().isEmpty()){

            return AccountResponse.builder()
                    .transactionStatusCode(BAD_REQUEST)
                    .transactionStatusDescription(EMPTY_EMAIL_MESSAGE)
                    .build();
        }

        Account account = accountMapper(request);

        Savings accountSavings = createSavingsAccount(account);

        account.setSavings(accountSavings);


        Account savedAccount =  accountRepository.save(account);

        if (savedAccount != null && savedAccount.getId() != null) {

           return AccountResponse.builder()
                    .customerNumber(savedAccount.getCustomerNumber())
                    .transactionStatusCode(TransactionStatusEnum.SUCCESS.getStatusCode())
                    .transactionStatusDescription(TransactionStatusEnum.SUCCESS.getStatusDescription())
                    .build();
        } else {
            log.error("=====______ Account Save Failed ________====== : {} ", LocalDateTime.now());
            throw new AccountSaveFailedException();
        }

    }


    /**
     * Get customer information by Customer Number
     * @param customerNumber
     * @return CustomerInfoResponse
     */
    @Override
    public CustomerInfoResponse getCustomerByCustomerNumber(Long customerNumber) {

        CustomerInfoResponse customerInfoResponse = new CustomerInfoResponse();

        Optional<Account> accountOptional = Optional.ofNullable(accountRepository.findByCustomerNumber(customerNumber));


        if(accountOptional.isEmpty()){
            throw new AccountNotFoundException("Account not found");
        }

        Account account = accountOptional.get();

        Optional<Savings> savingsOptional = Optional.ofNullable(savingsRepository.findByAccountId(account.getId()));

        customerInfoResponse.setCustomerNumber(account.getCustomerNumber());
        customerInfoResponse.setCustomerName(account.getCustomerName());
        customerInfoResponse.setCustomerMobile(account.getCustomerMobile());
        customerInfoResponse.setCustomerEmail(account.getCustomerEmail());
        customerInfoResponse.setAddress1(account.getAddress1());
        customerInfoResponse.setAddress2(account.getAddress2());
        customerInfoResponse.setSavings(savingsOptional.get());
        customerInfoResponse.setTransactionStatusCode("302");
        customerInfoResponse.setTransactionStatusDescription("Customer Account found");

        return customerInfoResponse;
    }



    /**
     * Helper method to build Account Object from CreateAccountRequest.
     * @param request
     * @return Account
     */
    private static Account accountMapper(CreateAccountRequest request) {


        Account account = new Account();

        account.setCustomerName(request.getCustomerName());
        account.setCustomerEmail(request.getCustomerEmail());
        account.setCustomerMobile(request.getCustomerMobile());
        account.setAddress1(request.getAddress1());
        account.setAddress2(request.getAddress2());

        if(request.getAccountType().equals(CHECKING)){
            account.setAccountTypeEnum(AccountTypeEnum.CHECKING.getCode());
        }else if(request.getAccountType().equals(SAVINGS)){
            account.setAccountTypeEnum(AccountTypeEnum.SAVINGS.getCode());
        }

        //Generate the customerNumber
        Long generateCustomerNumber = 10000000L + (long) (secureRandom.nextDouble() * 90000000L);


        account.setCustomerNumber(generateCustomerNumber);

        return account;
    }

    /**
     * Helper method to create Savings for the created Account
     * @param account
     * @return Savings
     */
    private static Savings createSavingsAccount(Account account){

        Savings savings = new Savings();
        Long generateAccountNumber = 10000L + (long) (secureRandom.nextDouble() * 90000L);

        savings.setAccountNumber(generateAccountNumber);
        savings.setAccount(account);
        savings.setAvailableBalance(BigDecimal.ZERO);
        savings.setAccountType(account.getAccountTypeEnum());


        return savings;

    }
}
