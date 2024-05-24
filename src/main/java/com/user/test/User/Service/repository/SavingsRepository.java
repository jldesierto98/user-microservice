package com.user.test.User.Service.repository;

import com.user.test.User.Service.entity.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface SavingsRepository extends JpaRepository<Savings, Long> {

    Savings findByAccountId(Long accountId);

}
