package bank.service;

import bank.domain.AccountType;

import java.util.EnumMap;
import java.util.Map;

public class AccountFactoryProvider {
    private final Map<AccountType, AccountFactory> factories = new EnumMap<>(AccountType.class);

    public AccountFactoryProvider() {
        register(new CheckingAccountFactory());
        register(new SavingsAccountFactory());
    }

    public AccountFactory get(AccountType type) {
        AccountFactory factory = factories.get(type);
        if (factory == null) {
            throw new IllegalArgumentException("Tipo de conta indisponivel");
        }
        return factory;
    }

    private void register(AccountFactory factory) {
        factories.put(factory.getType(), factory);
    }
}
