package com.aectann.presentation.commands;

import com.aectann.domain.issues.AccountIssue;
import com.aectann.presentation.exceptions.IllegalCommandException;

/**
 * Класс - реализация команды NEWACCOUNT.
 */
public class CreateAccountCommand extends AccountCommand {

    private static String COMMAND_NAME = "NEWACCOUNT";
    private static int NUMBER_ARGS = 1;


    public CreateAccountCommand(AccountIssue accountIssue) {
        super(COMMAND_NAME, NUMBER_ARGS, accountIssue);
    }


    public String execute(String[] args) throws IllegalCommandException {
        checkNumberArgs(args);
        final int accountNumber = parseAccountNumber(args[0]);
        if (getAccountIssue().CreateAccount(accountNumber)) {
            return getCommandSuccess();
        } else {
            return getCommandFailrue();
        }
    }
}
