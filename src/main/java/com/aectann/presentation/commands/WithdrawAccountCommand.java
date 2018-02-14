package com.aectann.presentation.commands;

import com.aectann.domain.issues.AccountIssue;
import com.aectann.presentation.exceptions.IllegalCommandException;

import java.math.BigDecimal;

/**
 * Класс - реализация команды WITHDRAW.
 */
public class WithdrawAccountCommand extends AccountCommand {

    private static String COMMAND_NAME = "WITHDRAW";
    private static int NUMBER_ARGS = 2;


    public WithdrawAccountCommand(AccountIssue accountIssue) {
        super(COMMAND_NAME, NUMBER_ARGS, accountIssue);
    }


    public String execute(String[] args) throws IllegalCommandException {
        checkNumberArgs(args);
        final int accountNumber = parseAccountNumber(args[0]);
        final BigDecimal amount = parseAccountAmount(args[1]);
        if (getAccountIssue().WithdrawAccount(accountNumber, amount)) {
            return getCommandSuccess();
        } else {
            return getCommandFailrue();
        }
    }
}
