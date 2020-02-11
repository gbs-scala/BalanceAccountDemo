package com.transfer.balance.transferbetweenaccounts;

import com.transfer.balance.transferbetweenaccounts.dao.AccountDAO;

public abstract class DAOFactory {

	//if you use H2 databse
	public static final int H2 = 1;

	public abstract AccountDAO getAccountDAO();

	public abstract void populateTestData();

	public static DAOFactory getDAOFactory(int factoryCode) {
		return null;
	}
}
