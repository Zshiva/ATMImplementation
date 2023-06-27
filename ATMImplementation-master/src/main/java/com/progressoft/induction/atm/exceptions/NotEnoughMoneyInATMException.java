package com.progressoft.induction.atm.exceptions;

public class NotEnoughMoneyInATMException extends RuntimeException {
    public NotEnoughMoneyInATMException(String msg) {
        super(msg);
    }
}
