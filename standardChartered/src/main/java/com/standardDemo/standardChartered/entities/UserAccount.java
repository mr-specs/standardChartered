package com.standardDemo.standardChartered.entities;



import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_accounts")
@Data
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int accountId;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "account_type", nullable = false)
    private String accountType;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "available_balance", nullable = false)
    private double availableBalance;

    @Column(name = "current_balance", nullable = false)
    private double currentBalance;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User user;
}
