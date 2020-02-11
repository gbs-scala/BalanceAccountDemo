package com.transfer.balance.transferbetweenaccounts.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

public enum MoneyUtil {
	
    INSTANCE;

    static Logger log = LoggerFactory.getLogger(MoneyUtil.class);

    public static final BigDecimal zeroAmount = new BigDecimal(0).setScale(4, RoundingMode.HALF_EVEN);

    public boolean validateCode(String inputCode) {
        try {
            Currency instance = Currency.getInstance(inputCode);
            if(log.isDebugEnabled()){
                log.debug("Validate Currency Code: " + instance.getSymbol());
            }
            return instance.getCurrencyCode().equals(inputCode);
        } catch (Exception e) {
            log.warn("Cannot parse the input Currency Code, Validation Failed: ", e);
        }
        return false;
    }

}
