package com.aectann.presentation;

import com.aectann.presentation.exceptions.IllegalCommandException;

/**
 * Интерфейс команды введенной пользователем.
 */
public interface Command {

    String getName();
    String execute(String[] args) throws IllegalCommandException;
}
