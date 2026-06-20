import java.util.Scanner;

/**
 * Aplicação MeuPlano.AI — versão de ANÁLISE.
 *
 * Implementa o fluxo principal do UC01 (Gerar Plano de Aula) da forma mais
 * simples possível, direto no terminal. Toda a orquestração, a entrada/saída e
 * o uso do serviço de IA estão MISTURADOS aqui de propósito: este é o ponto de
 * partida que a fase de design vai refatorar.
 */
public class MeuPlanoApp {

    public static void main(String[] args) {
        new MeuPlanoApp().executarFluxo();
    }

    /** Fluxo principal do UC01: descrição → rascunho → melhorias → final → relatório. */
    public void executarFluxo() {
        Scanner entrada = new Scanner(System.in);
        ServicoIA ia = new ServicoIA();

        // 1. Tela inicial
        System.out.println("==================================================");
        System.out.println("  MeuPlano.AI — Gerador de Planos de Aula");
        System.out.println("==================================================");

        // 2. Descrição em linguagem natural
        System.out.print("\nDescreva o plano de aula que deseja gerar:\n> ");
        String descricao = entrada.nextLine();

        // 3-4. Gera o rascunho e exibe o "formulário" com os campos preenchidos
        PlanoDeAula plano = ia.gerarRascunho(descricao);
        System.out.println("\n--- Rascunho gerado (revise os campos) ---");
        System.out.println(plano.comoRelatorio());

        // 5. Laço de melhoria (fluxo alternativo 5.2: novas instruções para a IA)
        while (true) {
            System.out.print("Deseja enviar instruções para melhorar? (s/n)\n> ");
            String resposta = entrada.nextLine().trim().toLowerCase();
            if (!resposta.equals("s")) {
                break;
            }
            System.out.print("Quais ajustes a IA deve aplicar?\n> ");
            String instrucoes = entrada.nextLine();
            plano = ia.melhorarRascunho(plano, instrucoes);
            System.out.println("\n--- Rascunho atualizado ---");
            System.out.println(plano.comoRelatorio());
        }

        // 6-7. Gera a versão final e exibe como relatório
        plano = ia.gerarVersaoFinal(plano);
        System.out.println("\n>>> VERSÃO FINAL DO PLANO DE AULA <<<");
        System.out.println(plano.comoRelatorio());

        entrada.close();
    }
}
