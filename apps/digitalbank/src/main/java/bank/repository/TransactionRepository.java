package bank.repository;

import bank.domain.Transaction;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TransactionRepository extends TextRepository<Transaction> {
    public TransactionRepository(Path path) {
        super(path, new TransactionMapper());
    }

    public List<Transaction> findByAccountId(String accountId) {
        List<Transaction> transactions = new ArrayList<>();
        for (Transaction transaction : findAll()) {
            if (transaction.getAccountId().equals(accountId)) {
                transactions.add(transaction);
            }
        }
        transactions.sort(Comparator.comparing(Transaction::getOccurredAt).reversed());
        return transactions;
    }
}
