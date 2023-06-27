# Programming Assignment

### ATM
The Automated Teller Machine (ATM) is a convenient way for bank customers to withdraw money at any time, A simple ATM is 
represented in the interface `com.progressoft.induction.atm.ATM`. The ATM has a single method `withdraw()` that accepts 
the account number from which you want to withdraw and the amount of money.

When a user attempts to withdraw money from the ATM, it should check if the user have enough money in his/her account, 
The `com.progressoft.induction.atm.BankingSystem` interface provides the ability to check the account balance through 
the `getAccountBalance()` method. The interface also provides the ability to debit the account, i.e. deducting the 
withdrawn amount from the account.

The `withdraw()` method from the `com.progressoft.induction.atm.ATM` interface should return the money to the user only 
after verifying enough money in the account and debiting the requested amount from the account. 
`com.progressoft.induction.atm.exceptions.InsufficientFundsException` should be thrown if the account did not have 
enough  money in it.
 
The ATM itself have a limited supply of money in it, and a 
`com.progressoft.induction.atm.exceptions.NotEnoughMoneyInATMException` should be thrown if the user is trying to 
withdraw an amount greater than what the ATM have.

The test class `com.progressoft.induction.atm.ATMTest` will also help you understand what your code is expected to do.

##### Initial Balances
The following shows the initial accounts and balances in the BankingSystem:

| Account number | Balance |
|----------------|---------|
| 123456789      | 1,000.0 |
| 111111111      | 1,000.0 |
| 222222222      | 1,000.0 |
| 333333333      | 1,000.0 |
| 444444444      | 1,000.0 | 


The following shows the initial money inside the ATM

| Banknote value | Number |
|----------------|--------|
| Fifty JOD      | 10     |
| Twenty JOD     | 20     |
| Ten JOD        | 100    |
| Five JOD       | 100    |


### Project Structure
This project is a maven project, you can start working on the project by importing it to your favorite IDE. The project 
contains some JUnit tests to help you verify your work. You can run these tests using the IDE or from the command line 
using the command `mvn clean test`.

 > Changing the Unit tests code is not allowed.

 > Changing the interfaces `ATM` and `BankingSystem` is not allowed.

### Required Delivery
You should deliver a working application that complies with the description above and make all the tests in the class 
`com.progressoft.induction.atm.ATMTest` pass successfully **without modifying the tests themselves**, the only 
thing you are allowed to modify in the test class is initializing the `atm` object with your implementation class inside 
the `setUp()` method. 

No user interface is required. 


### Evaluation Criteria
In order of importance 

* compiled code and all tests passing
* code quality and simplicity, you should follow Java coding conventions and try to find the simplest solution.
* proper user input validation
* code duplication, you should minimize duplication to the minimum


### Optional Delivery
A survey showed that ATM users prefer to receive banknotes with different values when withdrawing money, e.g. when 
withdrawing a 100 JODs, it is preferred to receive a mix of banknotes instead of two 50 JOD banknotes.

As an optional task, make sure the everytime a user withdraws from the ATM, it returns a mix of banknotes as diverse as 
possible.
