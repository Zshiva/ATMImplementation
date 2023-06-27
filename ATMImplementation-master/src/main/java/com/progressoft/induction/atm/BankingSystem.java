package com.progressoft.induction.atm;

import java.math.BigDecimal;

public interface BankingSystem {

    BigDecimal getAccountBalance(String accountNumber);

    void debitAccount(String accountNumber, BigDecimal amount);
}
