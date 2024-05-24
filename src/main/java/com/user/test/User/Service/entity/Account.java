package com.user.test.User.Service.entity;

import com.user.test.User.Service.enums.AccountTypeEnum;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "accounts")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "customer_number", nullable = false, unique = true)
    private Long customerNumber;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_mobile")
    private String customerMobile;


    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "address_1")
    private String address1;

    @Column(name = "address_2")
    private String address2;

    @Column(name = "account_type")
    private String accountTypeEnum;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Savings savings;

}
