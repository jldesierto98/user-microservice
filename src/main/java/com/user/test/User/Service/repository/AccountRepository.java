package com.user.test.User.Service.repository;

import com.user.test.User.Service.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT a FROM Account a WHERE a.customerNumber = :customerNumber")
    Account findByCustomerNumber(Long customerNumber);

}
