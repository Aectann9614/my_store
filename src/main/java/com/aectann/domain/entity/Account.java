package com.aectann.domain.entity;

import java.math.BigDecimal;

/**
 * Класс-сущность: счет. Реализует логику работы со счетом.
 */
public class Account {

    private int number;
    private BigDecimal balance;


    public Account(int number, BigDecimal balance) {
        setNumber(number);
        setBalance(balance);
    }


    private void setNumber(int number) {
        if (number < 0 || number > 99999) {
            throw new IllegalArgumentException("Account number must be non-negative and five-digit");
        }
        this.number = number;
    }

    private void setBalance(BigDecimal balance) {
        if (balance.signum() < 0) {
            throw new IllegalArgumentException("Balance must be non-negative");
        }
        this.balance = balance;
    }

    public int getNumber() {
        return number;
    }

    public BigDecimal getBalance() {
        return balance;
    }


    public void deposit(BigDecimal amount) {
        if (amount.signum() <= 0) {
            throw new IllegalArgumentException("Deposit amount must be non-negative");
        }
        setBalance(balance.add(amount));
    }

    public boolean withdraw(BigDecimal amount) {
        if (amount.signum() <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be non-negative");
        }
        try {
            setBalance(balance.subtract(amount));
            return true;
        } catch (IllegalArgumentException exception) {
            return false;
        }
    }
}
