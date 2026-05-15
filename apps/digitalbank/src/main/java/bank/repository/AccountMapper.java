package bank.repository;

import bank.domain.Account;
import bank.domain.AccountType;
import bank.domain.CheckingAccount;
import bank.domain.SavingsAccount;

import java.math.BigDecimal;

public class AccountMapper implements TextMapper<Account> {
    @Override
    public String toLine(Account item) {
        String extra = "";
        if (item instanceof CheckingAccount) {
            extra = ((CheckingAccount) item).getOverdraftLimit().toPlainString();
        }
        if (item instanceof SavingsAccount) {
            extra = ((SavingsAccount) item).getMonthlyRate().toPlainString();
        }
        return String.join("|",
                item.getId(),
                item.getCustomerId(),
                item.getType().name(),
                item.getAgency(),
                item.getNumber(),
                item.getBalance().toPlainString(),
                extra);
    }

    @Override
    public Account fromLine(String line) {
        String[] parts = line.split("\\|", -1);
        AccountType type = AccountType.valueOf(parts[2]);
        if (type == AccountType.CHECKING) {
            return new CheckingAccount(parts[0], parts[1], parts[3], parts[4], new BigDecimal(parts[5]), new BigDecimal(parts[6]));
        }
        return new SavingsAccount(parts[0], parts[1], parts[3], parts[4], new BigDecimal(parts[5]), new BigDecimal(parts[6]));
    }
}
