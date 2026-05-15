package bank.ui;

public interface Command {
    String getKey();

    String getLabel();

    boolean execute();
}
