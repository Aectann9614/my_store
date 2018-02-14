package com.aectann.domain.issues;

import com.aectann.data.AccountDAO;
import com.aectann.domain.entity.Account;

import java.math.BigDecimal;

/**
 * Данный класс реализует обработку операций, производимых со счетами.
 */
public class AccountIssue {

    private AccountDAO accountDAO;


    public AccountIssue(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public boolean CreateAccount(int accountNumber) {
        final Account account = new Account(accountNumber, new BigDecimal(0));
        return accountDAO.insertAccount(account);
    }

    public BigDecimal BalanceAccount(int accountNumber) {
        final Account account = accountDAO.getAccount(accountNumber);
        if (account == null) {
            return null;
        }
        return account.getBalance();
    }

    public boolean DepositAccount(int accountNumber, BigDecimal amount) {
        final Account account = accountDAO.getAccount(accountNumber);
        if (account == null) {
            return false;
        }
        account.deposit(amount);
        return accountDAO.updateAccount(account);
    }

    public boolean WithdrawAccount(int accountNumber, BigDecimal amount) {
        final Account account = accountDAO.getAccount(accountNumber);
        if (account == null || !account.withdraw(amount)) {
            return false;
        }
        return accountDAO.updateAccount(account);
    }
}
