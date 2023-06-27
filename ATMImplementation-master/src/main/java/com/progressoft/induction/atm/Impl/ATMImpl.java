package com.progressoft.induction.atm.Impl;

import com.progressoft.induction.atm.ATM;
import com.progressoft.induction.atm.Banknote;
import com.progressoft.induction.atm.exceptions.AccountNotFoundException;
import com.progressoft.induction.atm.exceptions.InsufficientFundsException;
import com.progressoft.induction.atm.exceptions.NotEnoughMoneyInATMException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ATMImpl implements ATM {
    private final BankingSystemImpl bankingSystem = new BankingSystemImpl();

    @Override
    public List<Banknote> withdraw(String accountNumber, BigDecimal amount) {
        // Validate the account number
        if (!isValidAccountNumber(accountNumber)) {
            throw new AccountNotFoundException("Invalid account number");
        }

        BigDecimal accountBalance = bankingSystem.getAccountBalance(accountNumber);
        validateAccountBalance(accountBalance, amount);
        validateATMBalance(amount);

        bankingSystem.debitAccount(accountNumber, amount);
        List<Banknote> banknotes = getBanknotes(amount);
        updateATMBalance(banknotes);

        return banknotes;
    }

    @Override
    public BigDecimal checkBalance(String accountNumber) {
        // Validate the account number
        if (!isValidAccountNumber(accountNumber)) {
            throw new AccountNotFoundException("Invalid account number");
        }

        return bankingSystem.getAccountBalance(accountNumber);
    }

    private void validateAccountBalance(BigDecimal accountBalance, BigDecimal amount) {
        if (accountBalance == null) {
            throw new AccountNotFoundException("Account not found");
        }

        if (accountBalance.compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient funds in your account");
        }
    }

    private void validateATMBalance(BigDecimal amount) {
        BigDecimal atmBalance = bankingSystem.sumOfMoneyInAtm();
        if (atmBalance.compareTo(amount) < 0) {
            throw new NotEnoughMoneyInATMException("Not enough money in ATM");
        }
    }

    private List<Banknote> getBanknotes(BigDecimal amount) {
        List<Banknote> banknotes = new ArrayList<>();
        BigDecimal remainingAmount = amount;

        List<Banknote> availableBanknotes = new ArrayList<>();
        for (Banknote banknote : Banknote.values()) {
            int banknoteCount = bankingSystem.getBanknoteCount(banknote);
            for (int i = 0; i < banknoteCount; i++) {
                availableBanknotes.add(banknote);
            }
        }
        Collections.shuffle(availableBanknotes);

        for (Banknote banknote : availableBanknotes) {
            BigDecimal banknoteValue = banknote.getValue();
            if (remainingAmount.compareTo(banknoteValue) >= 0) {
                banknotes.add(banknote);
                remainingAmount = remainingAmount.subtract(banknoteValue);
            }
        }

        if (remainingAmount.compareTo(BigDecimal.ZERO) > 0) {
            throw new NotEnoughMoneyInATMException("Cannot get the requested amount");
        }

        return banknotes;
    }

    private void updateATMBalance(List<Banknote> banknotes) {
        for (Banknote banknote : banknotes) {
            bankingSystem.decreaseBanknoteCount(banknote);
        }
    }

    // Custom account number validation method
    private boolean isValidAccountNumber(String accountNumber) {
        List<String> validAccountNumbers = Arrays.asList("123456789", "111111111", "222222222", "333333333", "444444444");
        return validAccountNumbers.contains(accountNumber);
    }
}
