package bank.service;

import bank.domain.Account;
import bank.domain.AccountType;
import bank.domain.SavingsAccount;
import bank.util.IdGenerator;

import java.math.BigDecimal;

public class SavingsAccountFactory implements AccountFactory {
    @Override
    public AccountType getType() {
        return AccountType.SAVINGS;
    }

    @Override
    public Account create(String customerId, String agency, String number) {
        return new SavingsAccount(IdGenerator.newId(), customerId, agency, number, BigDecimal.ZERO, new BigDecimal("0.005"));
    }
}
