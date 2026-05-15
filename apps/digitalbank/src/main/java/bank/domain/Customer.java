package bank.domain;

public class Customer implements Identifiable {
    private final String id;
    private final String name;
    private final String cpf;
    private final String email;
    private final String passwordHash;

    public Customer(String id, String name, String cpf, String email, String passwordHash) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
}
