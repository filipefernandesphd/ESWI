package bank.service;

import bank.domain.Account;
import bank.domain.TransactionType;

import java.math.BigDecimal;

public class CheckingFeePolicy implements FeePolicy {
    @Override
    public BigDecimal calculate(Account account, TransactionType type, BigDecimal amount) {
        if (type == TransactionType.TRANSFER_SENT) {
            return new BigDecimal("0.80");
        }
        if (type == TransactionType.BILL_PAYMENT) {
            return new BigDecimal("1.20");
        }
        if (type == TransactionType.WITHDRAW) {
            return new BigDecimal("0.30");
        }
        return BigDecimal.ZERO;
    }
}
