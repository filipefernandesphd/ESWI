# Digital Bank App

## Principais instruções

- Verifique se você possui os seguintes itens instalados no VSCode via Marketplace (estes não exigem admin):
  - `Extension Pack for Java`: (da Microsoft) Inclui suporte a Maven/Gradle e IntelliSense.
- Instale o plugin `Continue` no VSCode
- Verifique se o `Ollama` está instalado no computador

## Configuração da LLM local via Ollama

- Acesse o terminal e execute `ollama pull qwen2.5-coder:7b`
- Configure a extensão `Continue` do VSCode
  - Clique na opção `Configure models` (ícone de cubo)
  - Clique no botão de `+`
  - Selecione o provider como `Ollama` e selecione `Autodetect` em Model.
- Acesse o chat, escolha o modo `Chat` do `Continue`, selecione o modelo `qwen2.5-coder:7b (autodetected)` e envie o prompt para testar: `Olá! Você está rodando qual modelo?`
  - Obs: O modo `Agent` deve ser usado para ler e criar arquivos no projeto.

## Estrutura do projeto Java

```text
src/
└── main/
    └── java/
        ├── App.java
        └── bank/
            ├── application/
            │   └── BankConsoleApplication.java
            ├── domain/
            │   ├── Account.java
            │   ├── AccountType.java
            │   ├── CheckingAccount.java
            │   ├── Customer.java
            │   ├── Identifiable.java
            │   ├── SavingsAccount.java
            │   ├── Transaction.java
            │   └── TransactionType.java
            ├── repository/
            │   ├── AccountMapper.java
            │   ├── AccountRepository.java
            │   ├── CustomerMapper.java
            │   ├── CustomerRepository.java
            │   ├── Repository.java
            │   ├── TextMapper.java
            │   ├── TextRepository.java
            │   ├── TransactionMapper.java
            │   └── TransactionRepository.java
            ├── service/
            │   ├── AccountFactory.java
            │   ├── AccountFactoryProvider.java
            │   ├── AccountService.java
            │   ├── AuthService.java
            │   ├── BankingService.java
            │   ├── CheckingAccountFactory.java
            │   ├── CheckingFeePolicy.java
            │   ├── FeePolicy.java
            │   ├── FeePolicyRegistry.java
            │   ├── PersistingTransactionObserver.java
            │   ├── SavingsAccountFactory.java
            │   ├── SavingsFeePolicy.java
            │   └── TransactionObserver.java
            ├── ui/
            │   ├── Command.java
            │   ├── Menu.java
            │   ├── MenuCommand.java
            │   └── Terminal.java
            └── util/
                ├── Codec.java
                ├── IdGenerator.java
                ├── Money.java
                └── PasswordHasher.java
```
