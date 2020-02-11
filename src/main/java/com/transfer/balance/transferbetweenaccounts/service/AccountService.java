package com.transfer.balance.transferbetweenaccounts.service;

import com.transfer.balance.transferbetweenaccounts.DAOFactory;
import com.transfer.balance.transferbetweenaccounts.exceptions.CustomException;
import com.transfer.balance.transferbetweenaccounts.model.Account;
import com.transfer.balance.transferbetweenaccounts.model.MoneyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
public class AccountService {

    private final DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.H2);

    private static Logger log = LoggerFactory.getLogger(AccountService.class);

    @GetMapping(value = "/{accountNumber}")
    public Account getAccount(@PathParam("accountNumber") long accountNumber) throws CustomException {
        return daoFactory.getAccountDAO().getAccountByNumber(accountNumber);
    }

    @GetMapping("/{accountNumber}/balance")
    public BigDecimal getBalance(@PathParam("accountNumber") long accountNumber) throws CustomException {
        final Account account = daoFactory.getAccountDAO().getAccountByNumber(accountNumber);

        if(account == null){
            throw new WebApplicationException("Account not found", Response.Status.NOT_FOUND);
        }
        return account.getBalance();
    }

    @PutMapping("/{accountId}/deposit/{amount}")
    public Account deposit(@PathParam("accountId") long accountId,@PathParam("amount") BigDecimal amount) throws CustomException {

        if (amount.compareTo(MoneyUtil.zeroAmount) <=0){
            throw new WebApplicationException("Invalid Deposit amount", Response.Status.BAD_REQUEST);
        }

        daoFactory.getAccountDAO().updateAccountBalance(accountId,amount.setScale(4, RoundingMode.HALF_EVEN));
        return daoFactory.getAccountDAO().getAccountByNumber(accountId);
    }

    @PutMapping("/{accountId}/withdraw/{amount}")
    public Account withdraw(@PathParam("accountId") long accountNumber,@PathParam("amount") BigDecimal amount) throws CustomException {

        if (amount.compareTo(MoneyUtil.zeroAmount) <=0){
            throw new WebApplicationException("Invalid Deposit amount", Response.Status.BAD_REQUEST);
        }
        BigDecimal delta = amount.negate();
        if (log.isDebugEnabled())
            log.debug("Withdraw service: delta change to account  " + delta + " Account ID = " +accountNumber);
        daoFactory.getAccountDAO().updateAccountBalance(accountNumber,delta.setScale(4, RoundingMode.HALF_EVEN));
        return daoFactory.getAccountDAO().getAccountByNumber(accountNumber);
    }
}

