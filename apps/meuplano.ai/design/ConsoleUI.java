import java.util.Scanner;

/**
 * Isola a entrada e a saída pelo terminal.
 *
 * <p>Na fase de análise, as chamadas a {@code System.out} e {@code Scanner}
 * estavam espalhadas pela aplicação. Aqui elas ficam concentradas numa única
 * classe — quem orquestra o fluxo (o controller) não sabe se a interface é o
 * terminal, uma janela ou a web.</p>
 */
public class ConsoleUI {

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Lê uma linha digitada pelo usuário, mostrando um rótulo antes.
     *
     * @param rotulo o texto exibido antes da leitura
     * @return a linha digitada
     */
    public String lerLinha(String rotulo) {
        System.out.print(rotulo + "\n> ");
        return scanner.nextLine();
    }

    /**
     * Exibe um texto na saída.
     *
     * @param texto o conteúdo a exibir
     */
    public void exibir(String texto) {
        System.out.println(texto);
    }

    /** Fecha os recursos de entrada. */
    public void fechar() {
        scanner.close();
    }
}
