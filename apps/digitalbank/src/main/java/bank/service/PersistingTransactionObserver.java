package bank.service;

import bank.domain.Transaction;
import bank.repository.TransactionRepository;

public class PersistingTransactionObserver implements TransactionObserver {
    private final TransactionRepository repository;

    public PersistingTransactionObserver(TransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void onTransaction(Transaction transaction) {
        repository.save(transaction);
    }
}
