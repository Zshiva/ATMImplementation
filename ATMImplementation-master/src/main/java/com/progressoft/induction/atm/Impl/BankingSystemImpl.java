package com.progressoft.induction.atm.Impl;

import com.progressoft.induction.atm.BankingSystem;
import com.progressoft.induction.atm.Banknote;
import com.progressoft.induction.atm.exceptions.AccountNotFoundException;
import com.progressoft.induction.atm.exceptions.InsufficientFundsException;
import com.progressoft.induction.atm.exceptions.NotEnoughMoneyInATMException;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class BankingSystemImpl implements BankingSystem {
   Map<String, BigDecimal> accountBalanceMap = new HashMap<>();
   EnumMap<Banknote,Integer> atmCashMap = new EnumMap<>(Banknote.class);

    public BankingSystemImpl() {
        atmCashMap.put(Banknote.FIFTY_JOD,10);
        atmCashMap.put(Banknote.TWENTY_JOD,20);
        atmCashMap.put(Banknote.TEN_JOD,100);
        atmCashMap.put(Banknote.FIVE_JOD,100);

        accountBalanceMap.put("123456789", BigDecimal.valueOf(1000.0));
        accountBalanceMap.put("111111111", BigDecimal.valueOf(1000.0));
        accountBalanceMap.put("222222222", BigDecimal.valueOf(1000.0));
        accountBalanceMap.put("333333333", BigDecimal.valueOf(1000.0));
        accountBalanceMap.put("444444444", BigDecimal.valueOf(1000.0));
    }

    public BigDecimal sumOfMoneyInAtm(){
        BigDecimal total = BigDecimal.ZERO;
        for (Banknote banknote : Banknote.values()){
            BigDecimal banknoteValue = banknote.getValue();
            int banknoteCount = atmCashMap.get(banknote);
            total = total.add(banknoteValue.multiply(BigDecimal.valueOf(banknoteCount)));
        }
        return total;
    }

    @Override
    public BigDecimal getAccountBalance(String accountNumber){
        BigDecimal accountBalance = accountBalanceMap.get(accountNumber);
        if (accountBalance == null) {
            throw new AccountNotFoundException("Account not found");
        }
        return accountBalance;
    }

    @Override
    public void debitAccount(String accountNumber, BigDecimal amount) {
        BigDecimal accountBalance = accountBalanceMap.get(accountNumber);
        if (accountBalance == null) {
            throw new AccountNotFoundException("Account not found");
        }
        if (accountBalance.compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient funds in the account");
        }
        BigDecimal newBalance = accountBalance.subtract(amount);
        accountBalanceMap.put(accountNumber, newBalance);
    }
    public int getBanknoteCount(Banknote banknote) {
        return atmCashMap.get(banknote);
    }

    public void decreaseBanknoteCount(Banknote banknote) {
        int currentCount = atmCashMap.get(banknote);
        if (currentCount > 0) {
            atmCashMap.put(banknote, currentCount - 1);
        } else {
            throw new NotEnoughMoneyInATMException("Not enough banknotes in the ATM");
        }
    }
}
