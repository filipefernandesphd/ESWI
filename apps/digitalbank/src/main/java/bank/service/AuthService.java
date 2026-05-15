package bank.service;

import bank.domain.Customer;
import bank.repository.CustomerRepository;
import bank.util.IdGenerator;
import bank.util.PasswordHasher;

import java.util.Optional;

public class AuthService {
    private final CustomerRepository customers;

    public AuthService(CustomerRepository customers) {
        this.customers = customers;
    }

    public Customer register(String name, String cpf, String email, String password) {
        String normalizedCpf = normalizeCpf(cpf);
        require(name, "Nome obrigatorio");
        require(email, "Email obrigatorio");
        require(password, "Senha obrigatoria");
        if (normalizedCpf.length() != 11) {
            throw new IllegalArgumentException("CPF deve ter 11 digitos");
        }
        if (password.length() < 4) {
            throw new IllegalArgumentException("Senha deve ter pelo menos 4 caracteres");
        }
        if (customers.findByCpf(normalizedCpf).isPresent()) {
            throw new IllegalArgumentException("CPF ja cadastrado");
        }
        Customer customer = new Customer(IdGenerator.newId(), name.trim(), normalizedCpf, email.trim(), PasswordHasher.hash(password));
        customers.save(customer);
        return customer;
    }

    public Optional<Customer> login(String cpf, String password) {
        Optional<Customer> customer = customers.findByCpf(cpf);
        if (customer.isPresent() && PasswordHasher.matches(password, customer.get().getPasswordHash())) {
            return customer;
        }
        return Optional.empty();
    }

    private String normalizeCpf(String cpf) {
        return cpf.replaceAll("\\D", "");
    }

    private void require(String value, String message) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }
}
