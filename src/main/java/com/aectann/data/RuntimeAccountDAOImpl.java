package com.aectann.data;

import com.aectann.domain.entity.Account;

import java.util.HashMap;
import java.util.Map;

/**
 * Реализует CRUD-интерфейс для работы со счетами. Для хранения счетов используется Map. Работает только во время
 * выполнения программы, при перезапуске все изменения теряются.
 */
public class RuntimeAccountDAOImpl implements AccountDAO {

    private Map<Integer, Account> accountMap;


    public RuntimeAccountDAOImpl() {
        this.accountMap = new HashMap<Integer, Account>();
    }


    public boolean insertAccount(Account account) {
        if (accountMap.containsKey(account.getNumber())) {
            return false;
        }
        accountMap.put(account.getNumber(), account);
        return true;
    }

    public Account getAccount(int accountNumber) {
        if (!accountMap.containsKey(accountNumber)) {
            return null;
        }
        return accountMap.get(accountNumber);
    }

    public boolean updateAccount(Account account) {
        if (!accountMap.containsKey(account.getNumber())) {
            return false;
        }
        accountMap.replace(account.getNumber(), account);
        return true;
    }

    public boolean deleteAccount(int accountNumber) {
        if (!accountMap.containsKey(accountNumber)) {
            return false;
        }
        accountMap.remove(accountNumber);
        return true;
    }
}
