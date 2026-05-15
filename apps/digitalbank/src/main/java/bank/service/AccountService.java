package bank.service;

import bank.domain.Account;
import bank.domain.AccountType;
import bank.domain.Customer;
import bank.repository.AccountRepository;

import java.util.List;
import java.util.Optional;

public class AccountService {
    private static final String DEFAULT_AGENCY = "0001";

    private final AccountRepository accounts;
    private final AccountFactoryProvider factoryProvider;

    public AccountService(AccountRepository accounts, AccountFactoryProvider factoryProvider) {
        this.accounts = accounts;
        this.factoryProvider = factoryProvider;
    }

    public Account createAccount(Customer customer, AccountType type) {
        for (Account existing : accounts.findByCustomerId(customer.getId())) {
            if (existing.getType() == type) {
                throw new IllegalArgumentException("Cliente ja possui " + type.getDescription());
            }
        }
        Account account = factoryProvider.get(type).create(customer.getId(), DEFAULT_AGENCY, accounts.nextNumber());
        accounts.save(account);
        return account;
    }

    public List<Account> accountsOf(Customer customer) {
        return accounts.findByCustomerId(customer.getId());
    }

    public Optional<Account> findByNumber(String number) {
        return accounts.findByNumber(number);
    }
}
