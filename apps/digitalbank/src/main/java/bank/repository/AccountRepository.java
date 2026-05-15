package bank.repository;

import bank.domain.Account;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountRepository extends TextRepository<Account> {
    private static final int FIRST_NUMBER = 100001;

    public AccountRepository(Path path) {
        super(path, new AccountMapper());
    }

    public List<Account> findByCustomerId(String customerId) {
        List<Account> accounts = new ArrayList<>();
        for (Account account : findAll()) {
            if (account.getCustomerId().equals(customerId)) {
                accounts.add(account);
            }
        }
        return accounts;
    }

    public Optional<Account> findByNumber(String number) {
        String normalized = number.trim();
        for (Account account : findAll()) {
            if (account.getNumber().equals(normalized)) {
                return Optional.of(account);
            }
        }
        return Optional.empty();
    }

    public String nextNumber() {
        int max = FIRST_NUMBER - 1;
        for (Account account : findAll()) {
            try {
                int current = Integer.parseInt(account.getNumber());
                if (current > max) {
                    max = current;
                }
            } catch (NumberFormatException ignored) {
            }
        }
        return String.format("%06d", max + 1);
    }
}
