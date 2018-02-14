package com.aectann.presentation;

import com.aectann.data.AccountDAO;
import com.aectann.data.FileAccountDAOImpl;
import com.aectann.data.RuntimeAccountDAOImpl;
import com.aectann.domain.issues.AccountIssue;
import com.aectann.presentation.exceptions.IllegalCommandException;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Класс осуществляющий взаимодействие с консолью.
 */
public class ConsoleProcessor {

    public static void main(String[] args) {
        final AccountDAO accountDAO = new RuntimeAccountDAOImpl();
        final AccountIssue accountIssue = new AccountIssue(accountDAO);
        final CommandStorage commandStorage = new CommandStorage(accountIssue);
        final ConsoleProcessor consoleProcessor = new ConsoleProcessor(commandStorage);
        consoleProcessor.run();
    }

    private CommandStorage commandStorage;


    public ConsoleProcessor(CommandStorage commandStorage) {
        this.commandStorage = commandStorage;
    }

    public void run() {
        final Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                final String[] args = scanner.nextLine().split(" ");
                final Command command = commandStorage.getCommand(args[0]);
                final String[] params = Arrays.copyOfRange(args, 1, args.length);
                final String result = command.execute(params);
                System.out.println(result);
            } catch (IllegalCommandException commandException) {
                System.out.println(commandException.getMessage());
            }
        }
    }
}
