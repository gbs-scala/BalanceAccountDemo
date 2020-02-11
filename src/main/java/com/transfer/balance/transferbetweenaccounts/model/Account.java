package com.transfer.balance.transferbetweenaccounts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {
    @JsonIgnore
    private long accountNumber;

    @JsonProperty(required = true)
    private BigDecimal balance;

    public Account() {
        this.accountNumber = accountNumber;
    }

    public Account(long accountNumber, BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return getAccountNumber() == account.getAccountNumber() &&
                getBalance().equals(account.getBalance());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccountNumber(), getBalance());
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", balance=" + balance +
                '}';
    }
}
