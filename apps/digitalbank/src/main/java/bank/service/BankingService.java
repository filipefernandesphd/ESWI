package bank.service;

import bank.domain.Account;
import bank.domain.SavingsAccount;
import bank.domain.Transaction;
import bank.domain.TransactionType;
import bank.repository.AccountRepository;
import bank.util.IdGenerator;
import bank.util.Money;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BankingService {
    private final AccountRepository accounts;
    private final FeePolicyRegistry feePolicies;
    private final List<TransactionObserver> observers = new ArrayList<>();

    public BankingService(AccountRepository accounts, FeePolicyRegistry feePolicies) {
        this.accounts = accounts;
        this.feePolicies = feePolicies;
    }

    public void addObserver(TransactionObserver observer) {
        observers.add(observer);
    }

    public Transaction deposit(Account account, BigDecimal amount, String description) {
        Money.requirePositive(amount);
        account.deposit(amount);
        accounts.save(account);
        Transaction transaction = transaction(account, "", TransactionType.DEPOSIT, amount, BigDecimal.ZERO, description);
        notifyObservers(transaction);
        return transaction;
    }

    public Transaction withdraw(Account account, BigDecimal amount) {
        Money.requirePositive(amount);
        BigDecimal fee = feePolicies.calculate(account, TransactionType.WITHDRAW, amount);
        account.withdraw(Money.normalize(amount.add(fee)));
        accounts.save(account);
        Transaction transaction = transaction(account, "", TransactionType.WITHDRAW, amount, fee, "Saque em terminal");
        notifyObservers(transaction);
        return transaction;
    }

    public void transfer(Account source, Account target, BigDecimal amount) {
        Money.requirePositive(amount);
        if (source.getId().equals(target.getId())) {
            throw new IllegalArgumentException("Conta de origem e destino devem ser diferentes");
        }
        BigDecimal fee = feePolicies.calculate(source, TransactionType.TRANSFER_SENT, amount);
        source.withdraw(Money.normalize(amount.add(fee)));
        target.deposit(amount);
        accounts.saveAll(Arrays.asList(source, target));
        notifyObservers(transaction(source, target.getId(), TransactionType.TRANSFER_SENT, amount, fee, "Transferencia para " + target.getNumber()));
        notifyObservers(transaction(target, source.getId(), TransactionType.TRANSFER_RECEIVED, amount, BigDecimal.ZERO, "Transferencia de " + source.getNumber()));
    }

    public Transaction payBill(Account account, String code, BigDecimal amount) {
        Money.requirePositive(amount);
        String normalizedCode = code.trim();
        if (normalizedCode.length() < 5) {
            throw new IllegalArgumentException("Codigo de pagamento invalido");
        }
        BigDecimal fee = feePolicies.calculate(account, TransactionType.BILL_PAYMENT, amount);
        account.withdraw(Money.normalize(amount.add(fee)));
        accounts.save(account);
        Transaction transaction = transaction(account, "", TransactionType.BILL_PAYMENT, amount, fee, "Pagamento " + normalizedCode);
        notifyObservers(transaction);
        return transaction;
    }

    public Transaction applyYield(SavingsAccount account) {
        BigDecimal amount = account.applyYield();
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Conta sem saldo para rendimento");
        }
        accounts.save(account);
        Transaction transaction = transaction(account, "", TransactionType.YIELD, amount, BigDecimal.ZERO, "Rendimento de poupanca");
        notifyObservers(transaction);
        return transaction;
    }

    private Transaction transaction(Account account, String relatedAccountId, TransactionType type, BigDecimal amount, BigDecimal fee, String description) {
        return new Transaction(IdGenerator.newId(), account.getId(), relatedAccountId, type, amount, fee, description, LocalDateTime.now(), account.getBalance());
    }

    private void notifyObservers(Transaction transaction) {
        for (TransactionObserver observer : observers) {
            observer.onTransaction(transaction);
        }
    }
}
