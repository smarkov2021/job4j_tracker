package ru.job4j.bank;

import ru.job4j.bank.Account;
import ru.job4j.bank.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Класс описывает работу структуры, где ключем является пользователь, а значением список связанных с ним счетов.
 * структура устроена по принципу FIFO (first in - first out)
 * @author SERGEY MARKOV
 * @version 1.0
 */
public class BankService {

    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод принимает на вход объект типа user и добавляет его в очередь
     * при условии, что нет пользователя с таким же значением поля passport
     * @param user пользователь, которого необходимо добавить в структуру
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Метод удаляет пользователя с заданным значением поля passport
     * @param passport паспортные данные пользователя
     * @return результат удаления пользователя
     */
    public boolean deleteUser(String passport) {
        return users.remove(new User(passport, "")) != null;
    }

    /**
     * Метод добавляет счет для пользователя.
     * Осуществляется поиск пользователя по паспортным данным.
     * У найденного пользователя поднимаем список всех счетов, и добавляем новый.
     * Если пользователь не найден, выходим из метода
     * @param passport паспортные данные пользователя
     * @param account счет, который необходимо привязать
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        }
    }

    /**
     * Метод осуществляет поиск пользователя по его паспортным данным.
     * Если пользователь не найден, то результатом работы метода будет null.
     * @param passport паспортные данные пользователя.
     * @return пользователь с указанными паспортными данными.
     */
    public User findByPassport(String passport) {
        //
        return users.keySet()
                .stream()
                .filter(s -> s.getPassport().equals(passport))
                .findFirst()
                .orElse(null);
    }

    /**
     * Метод осуществляет поиск счета пользователя по паспортным данным и реквизитам.
     * Сначала находим пользователя, и поднимаем  список всех его счетов.
     * Последовательно перебирая список, находим совпадающий по полю requisite.
     * В случае, если пользователь не найден результат метода будет null.
     * @param passport паспортные данные пользователя
     * @param requisite данные реквизитов
     * @return возвращает счет пользователя, объект класса Account
     */
    public Account findByRequisite(String passport, String requisite) {
        //
        User user = findByPassport(passport);
        List<Account> userAccount = users.get(user);
        if (userAccount != null) {
            return userAccount
                    .stream()
                    .filter(s -> s.getRequisite().equals(requisite))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    /**
     * Метод позволяет перевести сумму с одного счета на другой.
     * @param srcPassport паспортные данные пользователя отправителя.
     * @param srcRequisite реквизиты счета списания.
     * @param destPassport паспортные данные пользователя получателя.
     * @param destRequisite реквизиты счета получателя.
     * @param amount сумма перевода.
     * @return результат выполнения операции
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        Account srcAccount = findByRequisite(srcPassport, srcRequisite);
        Account destAccount = findByRequisite(destPassport, destRequisite);
        if (srcAccount != null && destAccount != null && srcAccount.getBalance() >= amount) {
            srcAccount.setBalance(srcAccount.getBalance() - amount);
            destAccount.setBalance(destAccount.getBalance() + amount);
            return true;
        }
        return false;
    }

    /**
     * Вывод списка всех счетов у пользователя
     * @param user пользователь.
     * @return список счетов.
     */
    public List<Account> getAccounts(User user) {
        return users.get(user);
    }
}