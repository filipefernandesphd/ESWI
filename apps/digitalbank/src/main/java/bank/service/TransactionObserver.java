package bank.service;

import bank.domain.Transaction;

public interface TransactionObserver {
    void onTransaction(Transaction transaction);
}
