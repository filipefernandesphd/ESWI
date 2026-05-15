package bank.service;

import bank.domain.Account;
import bank.domain.AccountType;
import bank.domain.TransactionType;
import bank.util.Money;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;

public class FeePolicyRegistry {
    private final Map<AccountType, FeePolicy> policies = new EnumMap<>(AccountType.class);

    public FeePolicyRegistry() {
        policies.put(AccountType.CHECKING, new CheckingFeePolicy());
        policies.put(AccountType.SAVINGS, new SavingsFeePolicy());
    }

    public BigDecimal calculate(Account account, TransactionType type, BigDecimal amount) {
        FeePolicy policy = policies.get(account.getType());
        if (policy == null) {
            return BigDecimal.ZERO.setScale(2);
        }
        return Money.normalize(policy.calculate(account, type, amount));
    }
}
