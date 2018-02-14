package com.aectann.data;

import com.aectann.domain.entity.Account;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Реализует CRUD-интерфейс для работы со счетами. Для хранения счетов используется текстовый файл.
 */
public class FileAccountDAOImpl implements AccountDAO {

    private static String DELIM = ":";


    private File file;


    public FileAccountDAOImpl(String filePath) {
        try {
            this.file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean insertAccount(Account account) {
        final Map<Integer, Account> accountMap = readFile();
        if (accountMap.containsKey(account.getNumber())) {
            return false;
        }
        accountMap.put(account.getNumber(), account);
        writeFile(accountMap);
        return true;
    }

    public Account getAccount(int accountNumber) {
        final Map<Integer, Account> accountMap = readFile();
        if (!accountMap.containsKey(accountNumber)) {
            return null;
        }
        return accountMap.get(accountNumber);
    }

    public boolean updateAccount(Account account) {
        final Map<Integer, Account> accountMap = readFile();
        if (!accountMap.containsKey(account.getNumber())) {
            return false;
        }
        accountMap.replace(account.getNumber(), account);
        writeFile(accountMap);
        return true;
    }

    public boolean deleteAccount(int accountNumber) {
        final Map<Integer, Account> accountMap = readFile();
        if (!accountMap.containsKey(accountNumber)) {
            return false;
        }
        accountMap.remove(accountNumber);
        writeFile(accountMap);
        return true;
    }


    private Map<Integer, Account> readFile() {
        final Map<Integer, Account> map = new HashMap<Integer, Account>();
        try {
            final BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null && line.length() != 0) {
                String[] params = line.split(DELIM);
                final Account account = new Account(Integer.parseInt(params[0]), new BigDecimal(params[1]));
                map.put(account.getNumber(), account);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    private void writeFile(Map<Integer, Account> map) {
        try {
            final BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
            for (Map.Entry<Integer, Account> pair : map.entrySet()) {
                writer.write("" + pair.getValue().getNumber() + DELIM + pair.getValue().getBalance());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
