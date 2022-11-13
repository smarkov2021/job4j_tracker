package ru.job4j.collection;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class NotifyAccountTest {
    @Test
    public void whenTwoAccountsAddSent() {
        NotifyAccount notifyAccount = new NotifyAccount();
        List<Account> accounts = Arrays.asList(
                new Account("123", "Petr Arsentev", "eDer3432f"),
                new Account("142", "Petr Arsentev", "000001")
        );
        HashSet<Account> expect = new HashSet<>(
                Arrays.asList(
                        new Account("123", "Petr Arsentev", "eDer3432f"),
                        new Account("142", "Petr Arsentev", "000001")
                )
        );
        assertThat(notifyAccount.sent(accounts)).containsAll(expect);
    }

    @Test
    public void whenThreeAccountsAddTwoRecipients() {
        NotifyAccount notifyAccount = new NotifyAccount();
        List<Account> accounts = Arrays.asList(
                new Account("123", "Vasily Ivanov", "eDer3432f"),
                new Account("142", "Andrey Pogrebnyak", "000001"),
                new Account("123", "Victor", "3330001")
        );
        HashSet<Account> expect = new HashSet<>(
                Arrays.asList(
                        new Account("123", "Vasily Ivanov", "eDer3432f"),
                        new Account("142", "Andrey Pogrebnyak", "000001")
                )
        );
        assertThat(notifyAccount.sent(accounts)).containsAll(expect);
    }

    @Test
    public void whenFourAccountsAddOneRecipient() {
        NotifyAccount notifyAccount = new NotifyAccount();
        List<Account> accounts = Arrays.asList(
                new Account("123", "Vasily Ivanov", "eDer3432f"),
                new Account("123", "Andrey Pogrebnyak", "000001"),
                new Account("123", "Vasily Ivanov", "eDer3432f"),
                new Account("123", "Vladimir Zaytsev", "3330001")
        );
        HashSet<Account> expect = new HashSet<>(
                Arrays.asList(
                        new Account("123", "Vasily Ivanov", "eDer3432f")
                )
        );
        assertThat(notifyAccount.sent(accounts)).containsAll(expect);
    }
}