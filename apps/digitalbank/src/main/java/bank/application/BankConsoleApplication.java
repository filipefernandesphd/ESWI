package bank.application;

import bank.domain.Account;
import bank.domain.AccountType;
import bank.domain.Customer;
import bank.domain.SavingsAccount;
import bank.domain.Transaction;
import bank.repository.AccountRepository;
import bank.repository.CustomerRepository;
import bank.repository.TransactionRepository;
import bank.service.AccountFactoryProvider;
import bank.service.AccountService;
import bank.service.AuthService;
import bank.service.BankingService;
import bank.service.FeePolicyRegistry;
import bank.service.PersistingTransactionObserver;
import bank.ui.Command;
import bank.ui.Menu;
import bank.ui.MenuCommand;
import bank.ui.Terminal;
import bank.util.Money;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BankConsoleApplication {
    private final Terminal terminal;
    private final AuthService authService;
    private final AccountService accountService;
    private final BankingService bankingService;
    private final TransactionRepository transactions;
    private Customer currentCustomer;

    public BankConsoleApplication(Terminal terminal, AuthService authService, AccountService accountService, BankingService bankingService, TransactionRepository transactions) {
        this.terminal = terminal;
        this.authService = authService;
        this.accountService = accountService;
        this.bankingService = bankingService;
        this.transactions = transactions;
    }

    public static BankConsoleApplication createDefault() {
        Path data = Paths.get("data");
        CustomerRepository customerRepository = new CustomerRepository(data.resolve("customers.txt"));
        AccountRepository accountRepository = new AccountRepository(data.resolve("accounts.txt"));
        TransactionRepository transactionRepository = new TransactionRepository(data.resolve("transactions.txt"));
        AuthService authService = new AuthService(customerRepository);
        AccountService accountService = new AccountService(accountRepository, new AccountFactoryProvider());
        BankingService bankingService = new BankingService(accountRepository, new FeePolicyRegistry());
        bankingService.addObserver(new PersistingTransactionObserver(transactionRepository));
        return new BankConsoleApplication(new Terminal(), authService, accountService, bankingService, transactionRepository);
    }

    public void run() {
        List<Command> commands = new ArrayList<>();
        commands.add(new MenuCommand("1", "Abrir conta", this::register));
        commands.add(new MenuCommand("2", "Entrar", this::login));
        commands.add(new MenuCommand("0", "Encerrar", () -> false));
        new Menu(terminal, "Digital Bank", commands).show();
    }

    private boolean register() {
        String name = terminal.readRequired("Nome");
        String cpf = terminal.readRequired("CPF");
        String email = terminal.readRequired("Email");
        String password = terminal.readRequired("Senha");
        Customer customer = authService.register(name, cpf, email, password);
        Account account = accountService.createAccount(customer, chooseAccountType());
        terminal.message("Cliente cadastrado");
        printAccount(account);
        return true;
    }

    private boolean login() {
        String cpf = terminal.readRequired("CPF");
        String password = terminal.readRequired("Senha");
        Optional<Customer> customer = authService.login(cpf, password);
        if (!customer.isPresent()) {
            terminal.message("CPF ou senha invalidos");
            return true;
        }
        currentCustomer = customer.get();
        sessionMenu().show();
        currentCustomer = null;
        return true;
    }

    private Menu sessionMenu() {
        List<Command> commands = new ArrayList<>();
        commands.add(new MenuCommand("1", "Contas e saldo", this::showAccounts));
        commands.add(new MenuCommand("2", "Nova conta", this::createAccount));
        commands.add(new MenuCommand("3", "Depositar", this::deposit));
        commands.add(new MenuCommand("4", "Sacar", this::withdraw));
        commands.add(new MenuCommand("5", "Transferir", this::transfer));
        commands.add(new MenuCommand("6", "Pagar boleto", this::payBill));
        commands.add(new MenuCommand("7", "Extrato", this::statement));
        commands.add(new MenuCommand("8", "Render poupanca", this::applyYield));
        commands.add(new MenuCommand("9", "Meus dados", this::showCustomer));
        commands.add(new MenuCommand("0", "Sair", () -> false));
        return new Menu(terminal, "Conta de " + currentCustomer.getName(), commands);
    }

    private boolean showAccounts() {
        List<Account> accounts = accountService.accountsOf(currentCustomer);
        if (accounts.isEmpty()) {
            terminal.message("Nenhuma conta encontrada");
            return true;
        }
        for (Account account : accounts) {
            printAccount(account);
        }
        return true;
    }

    private boolean createAccount() {
        Account account = accountService.createAccount(currentCustomer, chooseAccountType());
        terminal.message("Conta criada");
        printAccount(account);
        return true;
    }

    private boolean deposit() {
        Account account = chooseOwnedAccount();
        if (account == null) {
            return true;
        }
        BigDecimal amount = terminal.readMoney("Valor");
        bankingService.deposit(account, amount, "Deposito em terminal");
        terminal.message("Deposito realizado. Saldo: " + Money.format(account.getBalance()));
        return true;
    }

    private boolean withdraw() {
        Account account = chooseOwnedAccount();
        if (account == null) {
            return true;
        }
        BigDecimal amount = terminal.readMoney("Valor");
        bankingService.withdraw(account, amount);
        terminal.message("Saque realizado. Saldo: " + Money.format(account.getBalance()));
        return true;
    }

    private boolean transfer() {
        Account source = chooseOwnedAccount();
        if (source == null) {
            return true;
        }
        String number = terminal.readRequired("Conta destino");
        Account target = accountService.findByNumber(number).orElseThrow(() -> new IllegalArgumentException("Conta destino nao encontrada"));
        BigDecimal amount = terminal.readMoney("Valor");
        bankingService.transfer(source, target, amount);
        terminal.message("Transferencia realizada. Saldo: " + Money.format(source.getBalance()));
        return true;
    }

    private boolean payBill() {
        Account account = chooseOwnedAccount();
        if (account == null) {
            return true;
        }
        String code = terminal.readRequired("Codigo do boleto");
        BigDecimal amount = terminal.readMoney("Valor");
        bankingService.payBill(account, code, amount);
        terminal.message("Pagamento realizado. Saldo: " + Money.format(account.getBalance()));
        return true;
    }

    private boolean statement() {
        Account account = chooseOwnedAccount();
        if (account == null) {
            return true;
        }
        List<Transaction> accountTransactions = transactions.findByAccountId(account.getId());
        if (accountTransactions.isEmpty()) {
            terminal.message("Extrato vazio");
            return true;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        for (Transaction transaction : accountTransactions) {
            String fee = transaction.getFee().compareTo(BigDecimal.ZERO) > 0 ? " Tarifa " + Money.format(transaction.getFee()) : "";
            terminal.message(formatter.format(transaction.getOccurredAt()) + " | " + transaction.getType().getDescription() + " | " + Money.format(transaction.getAmount()) + fee + " | " + transaction.getDescription() + " | Saldo " + Money.format(transaction.getBalanceAfter()));
        }
        return true;
    }

    private boolean applyYield() {
        Account account = chooseOwnedAccount();
        if (account == null) {
            return true;
        }
        if (!(account instanceof SavingsAccount)) {
            terminal.message("Rendimento disponivel apenas para poupanca");
            return true;
        }
        Transaction transaction = bankingService.applyYield((SavingsAccount) account);
        terminal.message("Rendimento aplicado: " + Money.format(transaction.getAmount()) + ". Saldo: " + Money.format(account.getBalance()));
        return true;
    }

    private boolean showCustomer() {
        terminal.message("Nome: " + currentCustomer.getName());
        terminal.message("CPF: " + currentCustomer.getCpf());
        terminal.message("Email: " + currentCustomer.getEmail());
        return true;
    }

    private Account chooseOwnedAccount() {
        List<Account> accounts = accountService.accountsOf(currentCustomer);
        if (accounts.isEmpty()) {
            terminal.message("Nenhuma conta encontrada");
            return null;
        }
        if (accounts.size() == 1) {
            return accounts.get(0);
        }
        for (int index = 0; index < accounts.size(); index++) {
            terminal.message((index + 1) + " - " + accounts.get(index).getDisplayName() + " " + Money.format(accounts.get(index).getBalance()));
        }
        while (true) {
            try {
                int option = Integer.parseInt(terminal.read("Conta"));
                if (option >= 1 && option <= accounts.size()) {
                    return accounts.get(option - 1);
                }
            } catch (NumberFormatException ignored) {
            }
            terminal.message("Conta invalida");
        }
    }

    private AccountType chooseAccountType() {
        terminal.message("1 - Conta Corrente");
        terminal.message("2 - Conta Poupanca");
        while (true) {
            String option = terminal.read("Tipo");
            if ("1".equals(option)) {
                return AccountType.CHECKING;
            }
            if ("2".equals(option)) {
                return AccountType.SAVINGS;
            }
            terminal.message("Tipo invalido");
        }
    }

    private void printAccount(Account account) {
        terminal.message(account.getDisplayName());
        terminal.message("Saldo: " + Money.format(account.getBalance()));
        terminal.message("Disponivel: " + Money.format(account.availableBalance()));
    }
}
