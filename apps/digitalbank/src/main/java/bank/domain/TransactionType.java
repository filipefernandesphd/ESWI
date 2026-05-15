package bank.domain;

public enum TransactionType {
    DEPOSIT("Deposito"),
    WITHDRAW("Saque"),
    TRANSFER_SENT("Transferencia enviada"),
    TRANSFER_RECEIVED("Transferencia recebida"),
    BILL_PAYMENT("Pagamento"),
    YIELD("Rendimento");

    private final String description;

    TransactionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
