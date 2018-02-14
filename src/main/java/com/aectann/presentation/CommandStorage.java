package com.aectann.presentation;

import com.aectann.domain.issues.AccountIssue;
import com.aectann.presentation.commands.BalanceAccountCommand;
import com.aectann.presentation.commands.CreateAccountCommand;
import com.aectann.presentation.commands.DepositAccountCommand;
import com.aectann.presentation.commands.WithdrawAccountCommand;
import com.aectann.presentation.exceptions.IllegalCommandException;

import java.util.HashMap;
import java.util.Map;

/**
 * Осуществляет инициализацию комманд и выдачу их по запросу.
 */
public class CommandStorage {

    private AccountIssue accountIssue;
    private Map<String, Command> commandMap;


    public CommandStorage(AccountIssue accountIssue) {
        this.accountIssue = accountIssue;
        commandMap = new HashMap<String, Command>();
        init();
    }

    private void init() {
        BalanceAccountCommand balanceAccountCommand = new BalanceAccountCommand(accountIssue);
        commandMap.put(balanceAccountCommand.getName(), balanceAccountCommand);

        CreateAccountCommand createAccountCommand = new CreateAccountCommand(accountIssue);
        commandMap.put(createAccountCommand.getName(), createAccountCommand);

        DepositAccountCommand depositAccountCommand = new DepositAccountCommand(accountIssue);
        commandMap.put(depositAccountCommand.getName(), depositAccountCommand);

        WithdrawAccountCommand withdrawAccountCommand = new WithdrawAccountCommand(accountIssue);
        commandMap.put(withdrawAccountCommand.getName(), withdrawAccountCommand);
    }

    public Command getCommand(String name) throws IllegalCommandException {
        if (!commandMap.containsKey(name)) {
            throw new IllegalCommandException(name, "This command not found");
        }
        return commandMap.get(name);
    }
}
