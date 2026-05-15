package bank.domain;

import bank.util.Money;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SavingsAccount extends Account {
    private final BigDecimal monthlyRate;

    public SavingsAccount(String id, String customerId, String agency, String number, BigDecimal balance, BigDecimal monthlyRate) {
        super(id, customerId, agency, number, balance);
        this.monthlyRate = monthlyRate;
    }

    @Override
    public AccountType getType() {
        return AccountType.SAVINGS;
    }

    @Override
    public BigDecimal availableBalance() {
        return getBalance();
    }

    public BigDecimal applyYield() {
        if (getBalance().compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }
        BigDecimal amount = Money.normalize(getBalance().multiply(monthlyRate));
        deposit(amount);
        return amount;
    }

    public BigDecimal getMonthlyRate() {
        return monthlyRate;
    }
}
