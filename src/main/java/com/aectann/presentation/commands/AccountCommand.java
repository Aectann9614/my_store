package com.aectann.presentation.commands;

import com.aectann.domain.issues.AccountIssue;
import com.aectann.presentation.Command;
import com.aectann.presentation.exceptions.IllegalCommandException;

import java.math.BigDecimal;

/**
 * Данный класс является обобщением команд, работающих со счетами.
 */
public abstract class AccountCommand implements Command {

    private static String COMMAND_SUCCESS = "OK";
    private static String COMMAND_FAILRUE = "ERROR";

    protected static String getCommandSuccess() {
        return COMMAND_SUCCESS;
    }
    protected static String getCommandFailrue() {
        return COMMAND_FAILRUE;
    }


    private String name;
    private int numberArgs;
    private AccountIssue accountIssue;


    public AccountCommand(String name, int NumberArgs, AccountIssue accountIssue) {
        this.name = name;
        this.accountIssue = accountIssue;
        this.numberArgs = numberArgs;
    }


    public String getName() {
        return name;
    }

    protected AccountIssue getAccountIssue() {
        return accountIssue;
    }


    protected int parseAccountNumber(String numberStr) throws IllegalCommandException {
        try {
            final int number = Integer.parseInt(numberStr);
            if (number < 0 || number > 99999 || numberStr.length() != 5) {
                final String message = "Argument 'account number' (" + numberStr + ") is not correct. " +
                        "It must be non-negative and five-digit number.";
                throw new IllegalCommandException(getName(), message);
            }
            return number;
        } catch (NumberFormatException exception) {
            final String message = "Argument 'account number' (" + numberStr + ") is not correct.";
            throw new IllegalCommandException(getName(), message);
        }
    }

    protected BigDecimal parseAccountAmount(String amountStr) throws IllegalCommandException {
        try {
            final BigDecimal amount = new BigDecimal(amountStr);
            if (amount.signum() < 0) {
                final String message = "Argument 'amount' (" + amountStr + ") is not correct.";
                throw new IllegalCommandException(getName(), message);
            }
            return amount;
        } catch (NumberFormatException exception) {
            final String message = "Argument 'amount' (" + amountStr + ") is not correct.";
            throw new IllegalCommandException(getName(), message);
        }
    }

    protected void checkNumberArgs(String[] args) throws IllegalCommandException {
        if (args.length != numberArgs) {
            throw new IllegalCommandException(getName(), "Number of arguments must be " + numberArgs + ".");
        }
    }

}
