package bank.repository;

import bank.domain.Customer;
import bank.util.Codec;

public class CustomerMapper implements TextMapper<Customer> {
    @Override
    public String toLine(Customer item) {
        return String.join("|",
                item.getId(),
                Codec.encode(item.getName()),
                item.getCpf(),
                Codec.encode(item.getEmail()),
                item.getPasswordHash());
    }

    @Override
    public Customer fromLine(String line) {
        String[] parts = line.split("\\|", -1);
        return new Customer(
                parts[0],
                Codec.decode(parts[1]),
                parts[2],
                Codec.decode(parts[3]),
                parts[4]);
    }
}
