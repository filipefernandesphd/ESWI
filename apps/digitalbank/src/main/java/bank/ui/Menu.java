package bank.ui;

import java.util.List;

public class Menu {
    private final Terminal terminal;
    private final String title;
    private final List<Command> commands;

    public Menu(Terminal terminal, String title, List<Command> commands) {
        this.terminal = terminal;
        this.title = title;
        this.commands = commands;
    }

    public void show() {
        boolean running = true;
        while (running) {
            terminal.blank();
            terminal.message(title);
            for (Command command : commands) {
                terminal.message(command.getKey() + " - " + command.getLabel());
            }
            String option = terminal.read("Opcao");
            Command selected = find(option);
            if (selected == null) {
                terminal.message("Opcao invalida");
                continue;
            }
            try {
                running = selected.execute();
            } catch (RuntimeException exception) {
                terminal.message("Erro: " + exception.getMessage());
            }
        }
    }

    private Command find(String key) {
        for (Command command : commands) {
            if (command.getKey().equals(key)) {
                return command;
            }
        }
        return null;
    }
}
