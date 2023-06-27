package com.progressoft.induction.atm;

import com.progressoft.induction.atm.Impl.ATMImpl;
import com.progressoft.induction.atm.exceptions.AccountNotFoundException;
import com.progressoft.induction.atm.exceptions.InsufficientFundsException;
import com.progressoft.induction.atm.exceptions.NotEnoughMoneyInATMException;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create an instance of the ATM implementation
        ATM atm = new ATMImpl();

        // Create an instance of the BankingSystem implementation
        // BankingSystem bankingSystem = new BankingSystemImpl();

        try {
            // Withdraw money from an account
            String accountNumber = "123456789";
            BigDecimal amount = new BigDecimal("40.0");
            List<Banknote> banknotes = atm.withdraw(accountNumber, amount);
            System.out.println("Withdrawn banknotes: " + banknotes);

            // Check the account balance
            BigDecimal balance = atm.checkBalance(accountNumber);
            System.out.println("Account balance: " + balance);

        } catch (AccountNotFoundException e) {
            System.out.println("Account not found: " + e.getMessage());
        } catch (InsufficientFundsException e) {
            System.out.println("Insufficient funds: " + e.getMessage());
        } catch (NotEnoughMoneyInATMException e) {
            System.out.println("Not enough money in the ATM: " + e.getMessage());
        }
    }
}
