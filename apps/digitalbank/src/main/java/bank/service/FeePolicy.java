package bank.service;

import bank.domain.Account;
import bank.domain.TransactionType;

import java.math.BigDecimal;

public interface FeePolicy {
    BigDecimal calculate(Account account, TransactionType type, BigDecimal amount);
}
