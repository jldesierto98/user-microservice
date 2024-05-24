package com.user.test.User.Service;

import com.user.test.User.Service.entity.Account;
import com.user.test.User.Service.entity.Savings;
import com.user.test.User.Service.enums.AccountTypeEnum;
import com.user.test.User.Service.repository.AccountRepository;
import com.user.test.User.Service.repository.SavingsRepository;
import com.user.test.User.Service.request.CreateAccountRequest;
import com.user.test.User.Service.response.AccountResponse;
import com.user.test.User.Service.service.AccountServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private SavingsRepository savingsRepository;

    @InjectMocks
    private AccountServiceImpl accountService;





    @Test
    public void testCreateAccount() {
        // Prepare test data
        CreateAccountRequest request = new CreateAccountRequest();
        request.setCustomerName("John Doe");
        request.setCustomerEmail("john@example.com");
        request.setCustomerMobile("1234567890");
        request.setAddress1("Address 1");
        request.setAddress2("Address 2");
        request.setAccountType("Savings");

        Account account = new Account();
        account.setCustomerName("John Doe");
        account.setCustomerEmail("john@example.com");
        account.setCustomerMobile("1234567890");
        account.setAddress1("Address 1");
        account.setAddress2("Address 2");
        account.setAccountTypeEnum(AccountTypeEnum.SAVINGS.getCode());
        account.setCustomerNumber(123456789L);

        Savings savings = new Savings();
        savings.setAccountNumber(12345L);
        savings.setAvailableBalance(BigDecimal.ZERO);
        savings.setAccountType(AccountTypeEnum.SAVINGS.getCode());

        // Mock behavior
        when(accountRepository.save(any(Account.class))).thenReturn(account);
        when(savingsRepository.save(any(Savings.class))).thenReturn(savings);

        // Call the method
        AccountResponse response = accountService.createAccount(request);

        // Verify the result
        assertEquals(123456789L, response.getCustomerNumber().longValue());
        assertEquals("SUCCESS", response.getTransactionStatusDescription());
    }
}
