package com.aectann.presentation.exceptions;

import java.io.IOException;

/**
 * Исключение, вызывемое при некорректно введенной команде.
 */
public class IllegalCommandException extends IOException {

    public IllegalCommandException(String commandName, String message) {
        super("Command " + commandName + " error. " + message);
    }
}
