package bank.service;

import bank.domain.Account;
import bank.domain.TransactionType;

import java.math.BigDecimal;

public class SavingsFeePolicy implements FeePolicy {
    @Override
    public BigDecimal calculate(Account account, TransactionType type, BigDecimal amount) {
        if (type == TransactionType.TRANSFER_SENT) {
            return new BigDecimal("0.50");
        }
        return BigDecimal.ZERO;
    }
}
