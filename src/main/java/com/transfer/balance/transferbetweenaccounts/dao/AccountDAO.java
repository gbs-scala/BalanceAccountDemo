package com.transfer.balance.transferbetweenaccounts.dao;

import com.transfer.balance.transferbetweenaccounts.exceptions.CustomException;
import com.transfer.balance.transferbetweenaccounts.model.UserTransaction;
import com.transfer.balance.transferbetweenaccounts.model.Account;

import java.math.BigDecimal;
import java.util.List;


public interface AccountDAO {

    Account getAccountByNumber(long accountNumber) throws CustomException;
    long createAccount(Account account) throws CustomException;
    int deleteAccountByNumber(long accountNumber) throws CustomException;

    int updateAccountBalance(long accountNumber, BigDecimal deltaAmount) throws CustomException;
    int transferAccountBalance(UserTransaction userTransaction) throws CustomException;
}
