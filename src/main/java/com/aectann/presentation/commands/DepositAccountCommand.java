package com.aectann.presentation.commands;

import com.aectann.domain.issues.AccountIssue;
import com.aectann.presentation.exceptions.IllegalCommandException;

import java.math.BigDecimal;

/**
 * Класс реализация команды DEPOSIT.
 */
public class DepositAccountCommand extends AccountCommand {

    private static String COMMAND_NAME = "DEPOSIT";
    private static int NUMBER_ARGS = 2;


    public DepositAccountCommand(AccountIssue accountIssue) {
        super(COMMAND_NAME, NUMBER_ARGS, accountIssue);
    }


    public String execute(String[] args) throws IllegalCommandException {
        checkNumberArgs(args);
        final int accountNumber = parseAccountNumber(args[0]);
        final BigDecimal amount = parseAccountAmount(args[1]);
        if (getAccountIssue().DepositAccount(accountNumber, amount)) {
            return getCommandSuccess();
        } else {
            return getCommandFailrue();
        }
    }
}
