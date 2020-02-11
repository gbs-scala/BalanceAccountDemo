package com.transfer.balance.transferbetweenaccounts.service;

import com.transfer.balance.transferbetweenaccounts.DAOFactory;
import com.transfer.balance.transferbetweenaccounts.model.UserTransaction;
import com.transfer.balance.transferbetweenaccounts.exceptions.CustomException;
import org.springframework.web.bind.annotation.PostMapping;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/transaction")
@Produces(MediaType.APPLICATION_JSON)
public class TransactionService {

    private final DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.H2);

    @PostMapping
    public Response transferFund(UserTransaction transaction) throws CustomException {

            int updateCount = daoFactory.getAccountDAO().transferAccountBalance(transaction);
            if (updateCount == 2) {
                return Response.status(Response.Status.OK).build();
            } else {
                throw new WebApplicationException("Transaction failed", Response.Status.BAD_REQUEST);
            }
    }
}

