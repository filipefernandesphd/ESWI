package bank.service;

import bank.domain.Account;
import bank.domain.AccountType;
import bank.domain.CheckingAccount;
import bank.util.IdGenerator;

import java.math.BigDecimal;

public class CheckingAccountFactory implements AccountFactory {
    @Override
    public AccountType getType() {
        return AccountType.CHECKING;
    }

    @Override
    public Account create(String customerId, String agency, String number) {
        return new CheckingAccount(IdGenerator.newId(), customerId, agency, number, BigDecimal.ZERO, new BigDecimal("500.00"));
    }
}
