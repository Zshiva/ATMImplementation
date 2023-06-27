package com.progressoft.induction.atm;
import com.progressoft.induction.atm.Impl.ATMImpl;
import com.progressoft.induction.atm.exceptions.AccountNotFoundException;
import com.progressoft.induction.atm.exceptions.NotEnoughMoneyInATMException;
import com.progressoft.induction.atm.exceptions.InsufficientFundsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

class ATMTest {

    private ATM atm;

    @BeforeEach
    void setUp() {
        //TODO: initialize the atm here
        this.atm=new ATMImpl();
    }
    @Test
    void givenAccountNumberThatDoesNotExist_whenWithdraw_thenShouldThrowException() {
        Assertions.assertThrows(AccountNotFoundException.class,
                () -> atm.withdraw("14141414141", new BigDecimal("120.0")));
    }

    @Test
    void givenValidAccountNumber_whenWithdrawAmountLargerThanTheAccountBalance_thenShouldThrowException() {
        Assertions.assertThrows(InsufficientFundsException.class,
                () -> atm.withdraw("123456789", new BigDecimal("20000.0")));
    }

    @Test
    void whenWithdrawAmountLargerThanWhatInMachine_thenShouldThrowException() {
        atm.withdraw("123456789", new BigDecimal("1000.0"));
        atm.withdraw("111111111", new BigDecimal("1000.0"));

        Assertions.assertThrows(NotEnoughMoneyInATMException.class,
                () -> atm.withdraw("444444444", new BigDecimal("500.0")));
    }

    @Test
    void whenWithdraw_thenSumOfReceivedBanknotesShouldEqualRequestedAmount() {
        BigDecimal requestedAmount = new BigDecimal(700);
        List<Banknote> receivedBanknotes = atm.withdraw("111111111", requestedAmount);

        BigDecimal sumOfAllBanknotes = receivedBanknotes.stream().map(Banknote::getValue).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

        Assertions.assertEquals(sumOfAllBanknotes.compareTo(requestedAmount), 0);
    }

    @Test
    void givenAllFundsInAccountAreWithdrwan_whenWithdraw_shouldThrowException() {
        atm.withdraw("222222222", new BigDecimal("500"));
        atm.withdraw("222222222", new BigDecimal("500"));

        Assertions.assertThrows(InsufficientFundsException.class,
                () -> atm.withdraw("222222222", new BigDecimal("500")));
    }
}
