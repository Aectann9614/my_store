package com.aectann.data;

import com.aectann.domain.entity.Account;

/**
 * CRUD - интерфейс для хранения аккаунтов.
 */
public interface AccountDAO {

    boolean insertAccount(Account account);

    Account getAccount(int accountNumber);

    boolean updateAccount(Account account);

    boolean deleteAccount(int accountNumber);
}
