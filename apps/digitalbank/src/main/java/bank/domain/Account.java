package bank.domain;

import bank.util.Money;

import java.math.BigDecimal;

public abstract class Account implements Identifiable {
    private final String id;
    private final String customerId;
    private final String agency;
    private final String number;
    private BigDecimal balance;

    protected Account(String id, String customerId, String agency, String number, BigDecimal balance) {
        this.id = id;
        this.customerId = customerId;
        this.agency = agency;
        this.number = number;
        this.balance = Money.normalize(balance);
    }

    @Override
    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getAgency() {
        return agency;
    }

    public String getNumber() {
        return number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void deposit(BigDecimal amount) {
        Money.requirePositive(amount);
        balance = Money.normalize(balance.add(amount));
    }

    public void withdraw(BigDecimal amount) {
        Money.requirePositive(amount);
        BigDecimal normalized = Money.normalize(amount);
        if (availableBalance().compareTo(normalized) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
        balance = Money.normalize(balance.subtract(normalized));
    }

    public String getDisplayName() {
        return getType().getDescription() + " " + agency + "/" + number;
    }

    public abstract AccountType getType();

    public abstract BigDecimal availableBalance();
}
