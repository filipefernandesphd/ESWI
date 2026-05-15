package bank.service;

import bank.domain.Account;
import bank.domain.AccountType;

public interface AccountFactory {
    AccountType getType();

    Account create(String customerId, String agency, String number);
}
