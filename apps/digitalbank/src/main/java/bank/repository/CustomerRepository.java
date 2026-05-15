package bank.repository;

import bank.domain.Customer;

import java.nio.file.Path;
import java.util.Optional;

public class CustomerRepository extends TextRepository<Customer> {
    public CustomerRepository(Path path) {
        super(path, new CustomerMapper());
    }

    public Optional<Customer> findByCpf(String cpf) {
        String normalized = normalizeCpf(cpf);
        for (Customer customer : findAll()) {
            if (customer.getCpf().equals(normalized)) {
                return Optional.of(customer);
            }
        }
        return Optional.empty();
    }

    private String normalizeCpf(String value) {
        return value.replaceAll("\\D", "");
    }
}
