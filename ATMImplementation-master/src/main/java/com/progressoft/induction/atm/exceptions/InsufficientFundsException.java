package com.progressoft.induction.atm.exceptions;

public class InsufficientFundsException extends RuntimeException{
    public InsufficientFundsException(String msg) {
        super(msg);
    }
}
