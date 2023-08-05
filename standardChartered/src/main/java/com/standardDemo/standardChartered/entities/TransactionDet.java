package com.standardDemo.standardChartered.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transactions")
@Data
public class TransactionDet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private int transactionId;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "account_id", nullable = false)
    private int accountId;

    @Column(name = "transaction_desc", nullable = false)
    private String transactionDesc;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "credit")
    private Double credit;

    @Column(name = "debit")
    private Double debit;

    @Column(name = "running_balance")
    private Double runningBalance;

    @Column(name = "current_balance")
    private Double currentBalance;

    @Column(name = "available_balance")
    private Double availableBalance;

    @Column(name = "transaction_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp transactionDate;
    
    @Transient
    private String transDate;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id", insertable = false, updatable = false)
    private UserAccount userAccount;
    
}
