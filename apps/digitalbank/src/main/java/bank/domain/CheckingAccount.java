package bank.domain;

import bank.util.Money;

import java.math.BigDecimal;

public class CheckingAccount extends Account {
    private final BigDecimal overdraftLimit;

    public CheckingAccount(String id, String customerId, String agency, String number, BigDecimal balance, BigDecimal overdraftLimit) {
        super(id, customerId, agency, number, balance);
        this.overdraftLimit = Money.normalize(overdraftLimit);
    }

    @Override
    public AccountType getType() {
        return AccountType.CHECKING;
    }

    @Override
    public BigDecimal availableBalance() {
        return Money.normalize(getBalance().add(overdraftLimit));
    }

    public BigDecimal getOverdraftLimit() {
        return overdraftLimit;
    }
}
