package bank.domain;

import bank.util.Money;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction implements Identifiable {
    private final String id;
    private final String accountId;
    private final String relatedAccountId;
    private final TransactionType type;
    private final BigDecimal amount;
    private final BigDecimal fee;
    private final String description;
    private final LocalDateTime occurredAt;
    private final BigDecimal balanceAfter;

    public Transaction(String id, String accountId, String relatedAccountId, TransactionType type, BigDecimal amount, BigDecimal fee, String description, LocalDateTime occurredAt, BigDecimal balanceAfter) {
        this.id = id;
        this.accountId = accountId;
        this.relatedAccountId = relatedAccountId;
        this.type = type;
        this.amount = Money.normalize(amount);
        this.fee = Money.normalize(fee);
        this.description = description;
        this.occurredAt = occurredAt;
        this.balanceAfter = Money.normalize(balanceAfter);
    }

    @Override
    public String getId() {
        return id;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getRelatedAccountId() {
        return relatedAccountId;
    }

    public TransactionType getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }

    public BigDecimal getBalanceAfter() {
        return balanceAfter;
    }
}
