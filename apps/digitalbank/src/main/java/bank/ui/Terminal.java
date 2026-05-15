package bank.ui;

import bank.util.Money;

import java.math.BigDecimal;
import java.util.Scanner;

public class Terminal {
    private final Scanner scanner = new Scanner(System.in);

    public String read(String label) {
        System.out.print(label + ": ");
        return scanner.nextLine().trim();
    }

    public String readRequired(String label) {
        while (true) {
            String value = read(label);
            if (!value.isEmpty()) {
                return value;
            }
            message("Valor obrigatorio");
        }
    }

    public BigDecimal readMoney(String label) {
        while (true) {
            try {
                BigDecimal value = Money.parse(read(label));
                Money.requirePositive(value);
                return value;
            } catch (RuntimeException exception) {
                message("Valor monetario invalido");
            }
        }
    }

    public void message(String message) {
        System.out.println(message);
    }

    public void blank() {
        System.out.println();
    }
}
