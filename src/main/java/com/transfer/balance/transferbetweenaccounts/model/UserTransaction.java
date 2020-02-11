package com.transfer.balance.transferbetweenaccounts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Objects;

public class UserTransaction {

    @JsonProperty(required = true)
    private Long sourceAccountNumber;

    @JsonProperty(required = true)
    private Long desctinationAccountNumber;

    @JsonProperty(required = true)
    private BigDecimal amount;

    public UserTransaction(Long sourceAccountNumber, Long desctinationAccountNumber, BigDecimal amount) {
        this.sourceAccountNumber = sourceAccountNumber;
        this.desctinationAccountNumber = desctinationAccountNumber;
        this.amount = amount;
    }

    public Long getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public Long getDesctinationAccountNumber() {
        return desctinationAccountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        UserTransaction that = (UserTransaction) o;

        if (!amount.equals(that.amount))
            return false;
        if (!sourceAccountNumber.equals(that.sourceAccountNumber))
            return false;
        return desctinationAccountNumber.equals(that.desctinationAccountNumber);

    }

    @Override
    public int hashCode() {
        return Objects.hash(getSourceAccountNumber(), getDesctinationAccountNumber(), getAmount());
    }

    @Override
    public String toString() {
        return "UserTransaction{" +
                "sourceAccountNumber=" + sourceAccountNumber +
                ", desctinationAccountNumber=" + desctinationAccountNumber +
                ", amount=" + amount +
                '}';
    }
}

