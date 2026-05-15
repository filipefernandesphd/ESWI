package bank.ui;

import java.util.function.BooleanSupplier;

public class MenuCommand implements Command {
    private final String key;
    private final String label;
    private final BooleanSupplier action;

    public MenuCommand(String key, String label, BooleanSupplier action) {
        this.key = key;
        this.label = label;
        this.action = action;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public boolean execute() {
        return action.getAsBoolean();
    }
}
