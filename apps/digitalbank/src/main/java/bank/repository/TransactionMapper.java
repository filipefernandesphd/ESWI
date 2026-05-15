package bank.repository;

import bank.domain.Transaction;
import bank.domain.TransactionType;
import bank.util.Codec;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionMapper implements TextMapper<Transaction> {
    @Override
    public String toLine(Transaction item) {
        return String.join("|",
                item.getId(),
                item.getAccountId(),
                item.getRelatedAccountId(),
                item.getType().name(),
                item.getAmount().toPlainString(),
                item.getFee().toPlainString(),
                Codec.encode(item.getDescription()),
                item.getOccurredAt().toString(),
                item.getBalanceAfter().toPlainString());
    }

    @Override
    public Transaction fromLine(String line) {
        String[] parts = line.split("\\|", -1);
        return new Transaction(
                parts[0],
                parts[1],
                parts[2],
                TransactionType.valueOf(parts[3]),
                new BigDecimal(parts[4]),
                new BigDecimal(parts[5]),
                Codec.decode(parts[6]),
                LocalDateTime.parse(parts[7]),
                new BigDecimal(parts[8]));
    }
}
