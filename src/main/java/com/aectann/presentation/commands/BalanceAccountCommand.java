package com.aectann.presentation.commands;

import com.aectann.domain.issues.AccountIssue;
import com.aectann.presentation.exceptions.IllegalCommandException;

import java.math.BigDecimal;

/**
 * Класс - реализация команды BALANCE.
 */
public class BalanceAccountCommand extends AccountCommand {

    private static String COMMAND_NAME = "BALANCE";
    private static int NUMBER_ARGS = 1;


    public BalanceAccountCommand(AccountIssue accountIssue) {
        super(COMMAND_NAME, NUMBER_ARGS, accountIssue);
    }


    public String execute(String[] args) throws IllegalCommandException {
        checkNumberArgs(args);
        final int accountNumber = parseAccountNumber(args[0]);
        final BigDecimal balance = getAccountIssue().BalanceAccount(accountNumber);
        if (balance == null) {
            return getCommandFailrue();
        } else {
            return balance.toString();
        }
    }
}
