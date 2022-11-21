package ru.job4j.bank;

import ru.job4j.bank.Account;
import ru.job4j.bank.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankService {
    private final Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
            List<Account> userAccount = new ArrayList<Account>();
            users.putIfAbsent(user, userAccount);
    }

    public boolean deleteUser(String passport) {
        if (findByPassport(passport) != null) {
            users.remove(findByPassport(passport));
            return findByPassport(passport) == null;
        }
        return false;
    }

    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> newAccount = users.get(user);
            if (newAccount == null) {
                ArrayList accounts = new ArrayList();
                accounts.add(account);
                users.put(user, accounts);
            } else if (!newAccount.contains(account)) {
                newAccount.add(account);
                users.put(user, newAccount);
            }
        }
    }

    public User findByPassport(String passport) {
        for (User user : users.keySet()) {
            String value = user.getPassport();
            if (passport.equals(value)) {
                return user;
            }
        }
        return null;
    }

    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> userAccount = users.get(user);
            for (Account obj : userAccount) {
                if (obj.getRequisite().equals(requisite)) {
                    return obj;
                }
            }
        }
        return null;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        Account srcAccount = findByRequisite(srcPassport, srcRequisite);
        Account destAccount = findByRequisite(destPassport, destRequisite);
        if (srcAccount != null && destAccount != null) {
            double srcBalance = srcAccount.getBalance() - amount;
            double destBalance = destAccount.getBalance()  + amount;
            if (srcBalance >= 0) {
                srcAccount.setBalance(srcBalance);
                destAccount.setBalance(destBalance);
                boolean rsl = true;
            }
        }
        return false;
    }

    public List<Account> getAccounts(User user) {
        return users.get(user);
    }
}